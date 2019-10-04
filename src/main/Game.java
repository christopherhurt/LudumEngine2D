package main;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Main class for the game engine. The game loop begins its execution here.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class Game {

    private static final AtomicReference<Scene> sCurrentScene = new AtomicReference<>();
    private static Integer sFpsCap;

    /**
     * Launches and starts the game. This method should be called at the end of the main method, as any code outside of
     * this method will not be executed after this is called.
     *
     * @param pInitialScene the initial game scene
     * @param pWidth the initial width of the window in pixels
     * @param pHeight the initial height of the window in pixels
     * @param pTitle the title of the window
     * @param pImagePath the relative file path of the window's image icon, can be null
     * @param pFpsCap the maximum frame rate, can be null to indicate no cap
     */
    public static void start(Scene pInitialScene, int pWidth, int pHeight, String pTitle, String pImagePath,
                             Integer pFpsCap) {
        Window.init(pWidth, pHeight, pTitle, pImagePath);
        sFpsCap = pFpsCap;
        setCurrentScene(pInitialScene);

        new Thread(() -> {
            // A copy of the objects list is made in case this event modifies the game objects in the scene
            new GameEvent(EventType.GAME_START, getCurrentScene()).fire(getCurrentScene().getCopyOfObjects());

            double lastCapCheck = Time.currentTime();
            double timeSinceLastCapCheck = 0d;

            double lastSecondCheck = Time.currentTime();
            double secondCounter = 0d;
            int frameCount = 0;

            while (true) {
                double currentTime = Time.currentTime();

                // Synchronize with fps cap
                if (sFpsCap != null) {
                    timeSinceLastCapCheck += (currentTime - lastCapCheck);
                    lastCapCheck = currentTime;

                    if (timeSinceLastCapCheck < 1d / sFpsCap) {
                        continue;
                    } else {
                        timeSinceLastCapCheck = 0d;
                    }
                }

                update();
                render();

                // Update fps counter
                if (Debug.isEnabled()) {
                    frameCount++;
                    secondCounter += (currentTime - lastSecondCheck);
                    lastSecondCheck = currentTime;

                    if (secondCounter >= 1d) {
                        System.out.println("FPS: " + frameCount);
                        frameCount = 0;
                        secondCounter = 0d;
                    }
                }
            }
        }).start();
    }

    /**
     * Updates the current scene.
     */
    private static void update() {
        Time.update();
        getCurrentScene().update();
    }

    /**
     * Does a render pass of the current scene.
     */
    private static void render() {
        BufferStrategy bufferStrategy = Window.getCanvas().getBufferStrategy();
        Graphics2D graphics = (Graphics2D)bufferStrategy.getDrawGraphics();

        getCurrentScene().render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * @return the current game scene
     */
    public static Scene getCurrentScene() {
        return sCurrentScene.get();
    }

    /**
     * Sets the current game scene.
     *
     * @param pScene the current scene to be set to
     */
    public static void setCurrentScene(Scene pScene) {
        // Unload previous scene
        if (getCurrentScene() != null) {
            new GameEvent(EventType.SCENE_UNLOAD, getCurrentScene()).fire(getCurrentScene().getCopyOfObjects());
        }

        sCurrentScene.set(pScene);

        // Load new scene
        new GameEvent(EventType.SCENE_LOAD, getCurrentScene()).fire(getCurrentScene().getCopyOfObjects());
    }

    /**
     * @return the fps cap as an {@link Optional}, which is empty if no cap exists
     */
    public static Optional<Integer> getFpsCap() {
        return Optional.ofNullable(sFpsCap);
    }

    /**
     * Sets the maximum frame rate.
     *
     * @param pFpsCap the fps cap to be set to
     */
    public static void setFpsCap(int pFpsCap) {
        sFpsCap = pFpsCap;
    }

    /**
     * Closes and ends the game.
     */
    public static synchronized void close() {
        // A copy of the objects list is made in case this event modifies the game objects in the scene
        new GameEvent(EventType.GAME_CLOSE, getCurrentScene()).fire(getCurrentScene().getCopyOfObjects());

        // This should be called last, as it terminates the game
        Window.destroy();
    }

    private Game() {
        // Static class, do not initialize
    }

}
