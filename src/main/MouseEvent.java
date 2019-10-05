package main;

import java.awt.geom.Point2D;
import java.util.Optional;

/**
 * Represents some form of mouse event, such as when the mouse is pressed, released, etc. This can be handled by a
 * game object's handler to perform some action when the mouse is used.
 *
 * @author Chris Hurt
 * @version 10.04.19
 */
public final class MouseEvent extends AInputEvent {

    /** The left mouse button code */
    public static final int BUTTON_LEFT = java.awt.event.MouseEvent.BUTTON1;

    /** The middle mouse button code */
    public static final int BUTTON_MIDDLE = java.awt.event.MouseEvent.BUTTON2;

    /** The right mouse button code */
    public static final int BUTTON_RIGHT = java.awt.event.MouseEvent.BUTTON3;

    /** The unknown mouse button code */
    public static final int BUTTON_UNKNOWN = -1;

    private int mButtonCode;

    /**
     * Package-private constructor.
     *
     * @param pType the mouse event type enum
     * @param pScene the scene in which the mouse event occurred
     * @param pScreenMouseLocation the location of the mouse cursor in screen space when the event occurred
     * @param pWorldMouseLocation the location of the mouse cursor in world space when the event occurred
     * @param pButtonCode the mouse button code
     */
    MouseEvent(EventType pType, Scene pScene, Optional<Point2D.Double> pScreenMouseLocation,
               Optional<Point2D.Double> pWorldMouseLocation, int pButtonCode) {
        super(pType, pScene, pScreenMouseLocation, pWorldMouseLocation);
        mButtonCode = checkValidButtonCode(pButtonCode);
    }

    /**
     * Checks that the button code used for this event is valid, i.e. one of the codes above.
     *
     * @param pButtonCode the button code being checked
     * @return the same button code if valid, BUTTON_UNKNOWN otherwise
     */
    static int checkValidButtonCode(int pButtonCode) {
        if (!(pButtonCode == BUTTON_LEFT || pButtonCode == BUTTON_MIDDLE || pButtonCode == BUTTON_RIGHT
            || pButtonCode == BUTTON_UNKNOWN)) {
            Debug.error("Invalid button code " + pButtonCode + " used for MouseEvent");
            return BUTTON_UNKNOWN;
        }

        return pButtonCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isValidEventType(EventType pType) {
        return pType == EventType.MOUSE_BUTTON_PRESSED || pType == EventType.MOUSE_BUTTON_RELEASED
                || pType == EventType.MOUSE_MOVED;
    }

    /**
     * @return the mouse button code associated with this event
     */
    public int getButtonCode() {
        return mButtonCode;
    }

}
