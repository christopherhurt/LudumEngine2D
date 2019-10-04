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
     * @return an optional containing the normalized location of the mouse cursor relative to the given game object
     *         relative to the dimension of the game object and its top-left corner, or an empty optional if the game
     *         object does not have a Transform component attached
     */
    public Optional<Point2D.Double> getPointRelativeToGameObject(GameObject pGameObject) {
        if (mScreenMouseLocation.isPresent()) {
            if (pGameObject.getTransform().isPresent() && pGameObject.getResolvedTransform().isPresent()) {
                Transform transform = pGameObject.getResolvedTransform().get();
                double normalizedX = (mScreenMouseLocation.get().getX() - transform.getX())
                        / transform.getScaleX() + 0.5;
                double normalizedY = (mScreenMouseLocation.get().getY() - transform.getY())
                        / transform.getScaleY() + 0.5;
                return Optional.of(new Point2D.Double(normalizedX, normalizedY));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
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
