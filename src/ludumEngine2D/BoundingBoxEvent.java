package ludumEngine2D;

import java.awt.geom.Point2D;

/**
 * Fired when an event occurs that's related to a bounding box. Note that this type of event is always fired as a result
 * of a mouse event, and consuming it will also consume the mouse event that fired it.
 *
 * NOTE - All bounding box events will be fired before the mouse event that generated it is propagated to the game
 *        objects in the scene. Additionally, if the bounding box event is consumed, the mouse event that generated it
 *        will be consumed.
 *
 * @author Chris Hurt
 * @version 10.05.19
 */
public final class BoundingBoxEvent extends AEvent {

    private Point2D.Double mRelativePoint;
    private int mButttonCode;

    /**
     * Package-private constructor.
     *
     * @param pType the event type enum
     * @param pScene the scene in which the event was fired
     * @param pRelativePoint the point at which the event occurred relative to the dimension of the bounding box and
     *                       its top-left corner
     * @param pButtonCode the mouse button code associated with this event
     */
    BoundingBoxEvent(EventType pType, Scene pScene, Point2D.Double pRelativePoint, int pButtonCode) {
        super(pType, pScene);
        mRelativePoint = pRelativePoint;
        mButttonCode = MouseEvent.checkValidButtonCode(pButtonCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isValidEventType(EventType pType) {
        return pType == EventType.BOUNDING_BOX_PRESSED || pType == EventType.BOUNDING_BOX_RELEASED;
    }

    /**
     * @return the point at which the event was fired relative to the dimension of the bounding box and its top-left
     * corner.
     */
    public Point2D.Double getRelativePoint() {
        return mRelativePoint;
    }

    /**
     * @return the mouse button code associated with the mouse event used to fire this event
     */
    public int getButtonCode() {
        return mButttonCode;
    }

}
