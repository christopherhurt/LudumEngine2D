package main;

/**
 * Represents an event that occurs in a GUI component. These are typically used to indicate scene or game configuration
 * changes, such as a "Play" button press on the main menu, or a modification in a settings menu.
 *
 * @author Chris Hurt
 * @version 10.05.19
 */
public final class GUIEvent extends AEvent {

    /**
     * Constructor.
     *
     * @param pType the event type enum
     * @param pScene the scene in which the GUI event was fired
     */
    GUIEvent(EventType pType, Scene pScene) {
        super(pType, pScene);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidEventType(EventType pType) {
        return pType == EventType.GUI_BUTTON_PRESSED || pType == EventType.GUI_BUTTON_RELEASED;
    }

}
