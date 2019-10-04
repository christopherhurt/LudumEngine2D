package main;

import java.awt.geom.Point2D;
import java.util.Optional;

/**
 * Represents a key event that's fired when the user interacts with their keyboard, such as when a key is pressed or
 * released. This event can be handled by game object handlers to perform some action when the keyboard is used.
 *
 * @author Chris Hurt
 * @version 10.04.19
 */
public final class KeyEvent extends AInputEvent {

    private int mKeyCode;

    /**
     * Package-private constructor.
     *
     * @param pType the event type
     * @param pScene the scene in which the event occurred
     * @param pScreenMouseLocation the location of the mouse cursor in screen space when the event occurred
     * @param pWorldMouseLocation the location of the mouse cursor in world space when the event occurred
     * @param pKeyCode the key code representing the key associated with the event
     */
    KeyEvent(EventType pType, Scene pScene, Optional<Point2D.Double> pScreenMouseLocation,
             Optional<Point2D.Double> pWorldMouseLocation, int pKeyCode) {
        super(pType, pScene, pScreenMouseLocation, pWorldMouseLocation);
        mKeyCode = pKeyCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isValidEventType(EventType pType) {
        return pType == EventType.KEY_PRESSED || pType == EventType.KEY_RELEASED;
    }

    /**
     * @return the key code associated with the event
     */
    public int getKeyCode() {
        return mKeyCode;
    }

}
