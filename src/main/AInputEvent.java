package main;

import java.awt.geom.Point2D;
import java.util.Optional;

/**
 * Represents a generic input event. Every input event provides information regarding the position of the mouse cursor
 * in the window at the time the event occurred. This is useful for determining if the mouse was hovered over a
 * particular game object when the event occurred.
 *
 * @author Chris Hurt
 * @version 10.04.19
 */
public abstract class AInputEvent extends AEvent {

    private Optional<Point2D.Double> mScreenMouseLocation;
    private Optional<Point2D.Double> mWorldMouseLocation;

    /**
     * Package-private constructor.
     *
     * @param pType the event type enum
     * @param pScene the scene in which the event occurred
     * @param pScreenMouseLocation the location of the mouse cursor in screen space when the event occurred
     * @param pWorldMouseLocation the location of the mouse cursor in world space when the event occurred
     */
    AInputEvent(EventType pType, Scene pScene, Optional<Point2D.Double> pScreenMouseLocation,
                Optional<Point2D.Double> pWorldMouseLocation) {
        super(pType, pScene);
        mScreenMouseLocation = pScreenMouseLocation;
        mWorldMouseLocation = pWorldMouseLocation;
    }

    /**
     * @return an optional containing the location of the mouse in normalized screen space relative to the dimension of
     *         the window, or an empty optional if the mouse wasn't inside the window
     */
    public Optional<Point2D.Double> getScreenMouseLocation() {
        return mScreenMouseLocation;
    }

    /**
     * @return an optional containing the location of the mouse in world space relative to the height of the window, or
     *         an empty optional if the mouse wasn't inside the window
     */
    public Optional<Point2D.Double> getWorldMouseLocation() {
        return mWorldMouseLocation;
    }

}
