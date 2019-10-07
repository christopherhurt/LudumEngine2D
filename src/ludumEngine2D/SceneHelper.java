package ludumEngine2D;

/**
 * Helper game object with scene management handler. This game object will be added with the minimum z-index when the
 * scene is created.
 *
 * @author Chris Hurt
 * @version 10.05.19
 */
final class SceneHelper extends GameObject {

    /**
     * Package-private constructor.
     *
     * @param pScene the scene to provide handler utility for
     */
    SceneHelper(Scene pScene) {
        super(null, Integer.MIN_VALUE);
        attachHandler((pEvt, pSelf) -> {
            if (pEvt instanceof MouseEvent) {
                MouseEvent mouseEvent = (MouseEvent)pEvt;

                // Pass to GUI buttons
                // A copy of the game objects list is used in case any of the handlers of the GUI events modify the
                // scene
                Scene.generateGUIEvents(mouseEvent, pScene.getCopyOfObjects());

                // Check if bounding box events should be fired
                // These events can only be fired if the mouse was pressed or released
                if (pEvt.getType() == EventType.MOUSE_BUTTON_PRESSED
                        || pEvt.getType() == EventType.MOUSE_BUTTON_RELEASED) {
                    Scene.generateBoundingBoxEvents(mouseEvent, pScene.getCopyOfObjects());
                }
            }
        });
    }

}
