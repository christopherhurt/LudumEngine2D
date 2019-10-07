package ludumEngine2D;

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

    private char mKey;

    /**
     * Package-private constructor.
     *
     * @param pType the event type
     * @param pScene the scene in which the event occurred
     * @param pScreenMouseLocation the location of the mouse cursor in screen space when the event occurred
     * @param pWorldMouseLocation the location of the mouse cursor in world space when the event occurred
     * @param pKey the char representation of the key that was pressed
     */
    KeyEvent(EventType pType, Scene pScene, Optional<Point2D.Double> pScreenMouseLocation,
             Optional<Point2D.Double> pWorldMouseLocation, char pKey) {
        super(pType, pScene, pScreenMouseLocation, pWorldMouseLocation);
        mKey = pKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isValidEventType(EventType pType) {
        return pType == EventType.KEY_PRESSED || pType == EventType.KEY_RELEASED;
    }

    /**
     * @return the key associated with the event
     */
    public char getKey() {
        return mKey;
    }

}
