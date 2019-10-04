package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a scene in the game.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class Scene {

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
    }

    /**
     * Updates all of the game objects and menu components in this scene.
     */
    void update() {
        // Sort the game objects by z-index
        mGameObjects.sort(Comparator.comparingInt(GameObject::getZIndex));

        // Update kinematics components
        mGameObjects.forEach(obj ->
            obj.getKinematics().ifPresent(kinematics -> {
                if (obj.getTransform().isPresent()) {
                    kinematics.update(obj.getTransform().get());
                } else {
                    Debug.error("GameObject with id "
                            + obj.getId() + " has kinematics component without a transform component");
                }
            })
        );

        // TODO: other updates here

        mCamera.update();
    }

    /**
     * Renders all of the game objects and menu components in this scene.
     *
     * @param pGraphics the graphics context used for rendering
     */
    void render(Graphics2D pGraphics) {
        // Clear the screen
        pGraphics.setBackground(mClearColor);
        Dimension windowDimension = Window.getDimension();
        pGraphics.clearRect(0, 0, (int)windowDimension.getWidth(), (int)windowDimension.getHeight());

        // Render appearance components
        pGraphics.translate(Window.normalizedToScreen(-mCamera.getX()), Window.normalizedToScreen(-mCamera.getY()));
        mGameObjects.forEach(obj ->
            obj.getAppearance().ifPresent(appearance -> {
                if (obj.getTransform().isPresent()) {
                    appearance.updateAndRender(pGraphics, obj.getTransform().get());
                } else {
                    Debug.error("GameObject with id "
                            + obj.getId() + " has appearance component without a transform component");
                }
            })
        );
    }

    /**
     * Adds a game object to the scene.
     *
     * @param pGameObject the game object to add
     * @return whether the game object was successfully added
     */
    public boolean add(GameObject pGameObject) {
        return mGameObjects.add(pGameObject);
    }

    /**
     * Removes a game object from the scene.
     *
     * @param pGameObject the game object to remove
     * @return whether the game object was successfully removed
     */
    public boolean remove(GameObject pGameObject) {
        return mGameObjects.remove(pGameObject);
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
