package ludumEngine2D;

/**
 * Represents a GUI component that is attachable to a game object. This class is defined as renderable, but serves as
 * a public interface to the user of the engine. This way, multiple GUI component implementations can be abstracted
 * without the ARenderable class being accessible to the user of the engine.
 *
 * @author Chris Hurt
 * @version 10.04.19
 */
public abstract class AGUIComponent extends ARenderable {

    /**
     * Overrideable method that updates this GUI component when it exists in the current scene and a mouse event occurs.
     * A GUI event may be fired in response to the mouse event, depending on what it is.
     *
     * @param pEvt the mouse event
     * @param pGameObject the game object this GUI component is attached to
     */
    void update(MouseEvent pEvt, GameObject pGameObject) {
        // Do nothing by default
        // Override if the GUI component should respond to mouse events
    }

}
