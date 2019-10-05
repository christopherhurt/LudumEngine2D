package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Represents a scene in the game. All game objects in the scene are updated and rendered using pre-ordered traversals
 * of the parentage tree.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class Scene {

    /**
     * The list of pending input events. This was made thread-safe because events will be enqueued from the main input
     * thread and processed on the game thread.
     * This queue is static because user inputs should be processed across all scenes, but contained in the Scene class
     * because processing of these events occurs here by the current scene.
     */
    private static final Queue<AInputEvent> PENDING_INPUT_EVENTS = new ConcurrentLinkedQueue<>();

    private List<GameObject> mGameObjects = new LinkedList<>();
    private ACamera mCamera;
    private Color mClearColor;

    /**
     * Constructor.
     *
     * @param pCamera the camera to use for this scene
     */
    public Scene(ACamera pCamera, Color pClearColor) {
        mCamera = pCamera;
        mClearColor = pClearColor;

        // Helper game object for passing mouse events to GUI buttons
        add(new GameObjectBldr().withHandler((pEvt, pSelf) -> {
            if (pEvt instanceof MouseEvent) {
                MouseEvent mouseEvent = (MouseEvent)pEvt;
                mGameObjects.forEach(obj -> obj.getGUIComponent().ifPresent(gui -> gui.update(mouseEvent, obj)));
            }
        }).build());
    }

    /**
     * Updates all of the game objects in this scene, called when this is the current scene.
     */
    void update() {
        // Sort the game objects by z-index and parentage
        sortGameObjectList(mGameObjects);

        // Update kinematics components
        updateKinematics(mGameObjects);

        // Resolve overall transforms based on parentage
        resolveTransforms(mGameObjects);

        // Update interpolation values
        // Even though interpolations are static across all scenes, these are updated here because interpolation events
        // are fired for game objects in the current scene, and game objects in the current scene should be sorted and
        // have updated transforms before handling interpolation events.
        InterpolationFactory.updateAll();

        // Process all pending input events. This needs to happen after transforms have been resolved and before other
        // update events occur.
        // A copy of the scene's game object list is made after every event is processed in the case an event makes
        // modifications to the scene
        while (!PENDING_INPUT_EVENTS.isEmpty()) {
            PENDING_INPUT_EVENTS.remove().fire(getCopyOfObjects());
        }

        // Update game objects
        // A copy of the game objects list is made in case one of the events modifies the game objects in the scene
        List<GameObject> copy = getCopyOfObjects();
        new GameEvent(EventType.UPDATE, this).fire(copy);
        new GameEvent(EventType.LATE_UPDATE, this).fire(copy);

        mCamera.update();
    }

    /**
     * Recursive helper method that sorts all game objects in the given list by z-index and parentage.
     *
     * @param pGameObjects the list of game objects to sort
     */
    private static void sortGameObjectList(List<GameObject> pGameObjects) {
        pGameObjects.sort(Comparator.comparingInt(GameObject::getZIndex));
        for (GameObject gameObject : pGameObjects) {
            sortGameObjectList(gameObject.getChildren());
        }
    }

    /**
     * Resolves overall screen space transforms based on parentage.
     *
     * @param pGameObjects the list of game objects to resolve
     */
    private static void resolveTransforms(List<GameObject> pGameObjects) {
        pGameObjects.forEach(obj -> {
            obj.resolveTransformFromParent();
            resolveTransforms(obj.getChildren());
        });
    }

    /**
     * Updates the kinematics components of all game objects in the given list and their children.
     *
     * @param pGameObjects the list of game objects to update
     */
    private static void updateKinematics(List<GameObject> pGameObjects) {
        pGameObjects.forEach(obj -> {
            obj.getKinematics().ifPresent(kinematics -> {
                if (obj.getTransform().isPresent()) {
                    kinematics.update(obj.getTransform().get());
                } else {
                    Debug.error("GameObject with id "
                            + obj.getId() + " has kinematics component without a transform component");
                }
            });
            updateKinematics(obj.getChildren());
        });
    }

    /**
     * Renders all of the game objects in this scene, called when this is the current scene.
     *
     * @param pGraphics the graphics context used for rendering
     */
    void render(Graphics2D pGraphics) {
        // Clear the screen
        pGraphics.setBackground(mClearColor);
        Dimension windowDimension = Window.getDimension();
        pGraphics.clearRect(0, 0, (int)windowDimension.getWidth(), (int)windowDimension.getHeight());

        // Render appearance components
        pGraphics.translate(Window.normalizedToScreen(-(mCamera.getX() - 0.5 * Window.getAspectRatio())),
                Window.normalizedToScreen(-(mCamera.getY() - 0.5)));
        renderGameObjects(mGameObjects, pGraphics);
    }

    /**
     * Recursively renders all children in the scene, including descendants.
     *
     * @param pGameObjects the game objects to render
     * @param pGraphics the graphics context to use for rendering
     */
    private static void renderGameObjects(List<GameObject> pGameObjects, Graphics2D pGraphics) {
        pGameObjects.forEach(obj -> {
            Optional<Transform> resolvedTransform = obj.getResolvedTransform();

            // Render appearance
            obj.getAppearance().ifPresent(appearance -> {
                if (resolvedTransform.isPresent()) {
                    appearance.render(pGraphics, resolvedTransform.get());
                } else {
                    Debug.error("GameObject with id "
                            + obj.getId() + " has appearance component without a resolved transform");
                }
            });

            // Render GUI component
            obj.getGUIComponent().ifPresent(gui -> {
                if (resolvedTransform.isPresent()) {
                    gui.render(pGraphics, resolvedTransform.get());
                } else {
                    Debug.error("GameObject with id "
                            + obj.getId() + " has GUI component without a resolved transform");
                }
            });

            // Render children
            renderGameObjects(obj.getChildren(), pGraphics);
        });
    }

    /**
     * @return a copy of the list of game objects in this scene
     */
    List<GameObject> getCopyOfObjects() {
        return new LinkedList<>(mGameObjects);
    }

    /**
     * Enqueues an input event to be processed by the current scene at the start of the next frame.
     *
     * @param pEvt the event to enqueue
     */
    static void enqueueInputEvent(AInputEvent pEvt) {
        PENDING_INPUT_EVENTS.add(pEvt);
    }

    /**
     * Adds a game object to the scene.
     *
     * @param pGameObject the game object to add
     * @return whether the game object was successfully added
     */
    public boolean add(GameObject pGameObject) {
        if (pGameObject.getParent().isPresent()) {
            Debug.error("GameObject with id "
                    + pGameObject.getId() + " has non-null parent and cannot be directly added to a scene");
            return false;
        }

        if (mGameObjects.contains(pGameObject)) {
            Debug.error("GameObject with id "
                    + pGameObject.getId() + " cannot be added to a scene multiple times");
            return false;
        }

        mGameObjects.add(pGameObject);
        new GameEvent(EventType.ADD_TO_SCENE, this).fire(List.of(pGameObject));
        return true;
    }

    /**
     * Removes a game object from the scene.
     *
     * @param pGameObject the game object to remove
     * @return whether the game object was successfully removed
     */
    public boolean remove(GameObject pGameObject) {
        boolean removed = mGameObjects.remove(pGameObject);
        if (removed) {
            new GameEvent(EventType.REMOVE_FROM_SCENE, this).fire(List.of(pGameObject));
        }
        return removed;
    }

    /**
     * Creates and returns a list of game objects in the scene with the specified tag.
     *
     * @param pTag the tag of game objects to retrieve
     * @return a list of game objects with the specified tag
     */
    public List<GameObject> getObjectsWithTag(String pTag) {
        return mGameObjects.stream().filter(obj ->
                obj.getTag().isPresent() && obj.getTag().get().equals(pTag)).collect(Collectors.toList());
    }

    /**
     * @return the number of game objects in the scene
     */
    public int getNumObjects() {
        return mGameObjects.size();
    }

    /**
     * Clears all game objects from the scene.
     */
    public void clear() {
        mGameObjects.clear();
    }

    /**
     * @return the camera used by this scene
     */
    public ACamera getCamera() {
        return mCamera;
    }

    /**
     * @return the color to clear the screen to when rendering this scene
     */
    public Color getClearColor() {
        return mClearColor;
    }

    /**
     * Sets the color to clear the screen to when rendering this scene
     *
     * @param pColor the color to be set to
     */
    public void setClearColor(Color pColor) {
        mClearColor = pColor;
    }

}
