package ludumEngine2D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * A bounding box component that can be used for interacting the game object this component is attached to with the
 * mouse. When the bounds defined by this component are selected or released, a BoundingBoxEvent is fired to the
 * game object that has this bounding box attached.
 *
 * @author Chris Hurt
 * @version 10.05.19
 */
public final class BoundingBox extends ARenderable {

    /** The outline color to render bounding boxes with when debug is enabled */
    private static final Color OUTLINE_COLOR = Color.RED;

    private double mRelativeWidth;
    private double mRelativeHeight;
    private double mRelativeOffsetX;
    private double mRelativeOffsetY;

    /**
     * Constructor.
     *
     * @param pRelativeWidth the normalized width of the bounding box relative to the sibling resolved transform
     * @param pRelativeHeight the normalized height of the bounding box relative to the sibling resolved transform
     */
    public BoundingBox(double pRelativeWidth, double pRelativeHeight) {
        this(pRelativeWidth, pRelativeHeight, 0d, 0d);
    }

    /**
     * Constructor.
     *
     * @param pRelativeWidth the normalized width of the bounding box relative to the sibling resolved transform
     * @param pRelativeHeight the normalized height of the bounding box relative to the sibling resolved transform
     * @param pRelativeOffsetX the normalized x offset of the bounding box relative to the sibling resolved transform
     * @param pRelativeOffsetY the normalized y offset of the bounding box relative to the sibling resolved transform
     */
    public BoundingBox(double pRelativeWidth, double pRelativeHeight, double pRelativeOffsetX,
                       double pRelativeOffsetY) {
        mRelativeWidth = pRelativeWidth;
        mRelativeHeight = pRelativeHeight;
        mRelativeOffsetX = pRelativeOffsetX;
        mRelativeOffsetY = pRelativeOffsetY;
    }

    /**
     * Checks if the given point is contained within this bounding box. The point should be normalized to the resolved
     * transform attached to the game object that this bounding box is attached to. Since the size and offset of this
     * bounding box is also relative to the same transform, this check is fairly straightforward.
     *
     * @param pPoint the point to check
     * @return whether the given point is contained within this bounding box
     */
    public boolean containsRelativePoint(Point2D.Double pPoint) {
        Point2D.Double relativePoint = convertRelativeTransformPointToRelativeBoundingBoxPoint(pPoint);
        return relativePoint.getX() >= 0d && relativePoint.getX() <= 1d
                && relativePoint.getY() >= 0d && relativePoint.getY() <= 1d;
    }

    /**
     * Converts a point in normalized units relative to the resolved transform attached to the game object that this
     * bounding box is attached to and its top-left corner to a point relative to this bounding box. This is useful in
     * determining which point on the bounding box was interacted with when a bounding box event is fired.
     *
     * @param pPoint the point relative to the described transform
     * @return the point relative to this bounding box
     */
    Point2D.Double convertRelativeTransformPointToRelativeBoundingBoxPoint(Point2D.Double pPoint) {
        double relativeX = (pPoint.getX() - mRelativeOffsetX) / mRelativeWidth;
        double relativeY = (pPoint.getY() - mRelativeOffsetY) / mRelativeHeight;
        return new Point2D.Double(relativeX, relativeY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void render(Graphics2D pGraphics, Transform pTransform) {
        pGraphics.setColor(OUTLINE_COLOR);

        int width = Window.normalizedToScreen(pTransform.getScaleX() * mRelativeWidth);
        int height = Window.normalizedToScreen(pTransform.getScaleY() * mRelativeHeight);
        int x = Window.normalizedToScreen(pTransform.getX() + mRelativeOffsetX * pTransform.getScaleX()) - width / 2;
        int y = Window.normalizedToScreen(pTransform.getY() + mRelativeOffsetY * pTransform.getScaleY()) - height / 2;
        pGraphics.drawRect(x, y, width, height);
    }

    /**
     * @return the relative width
     */
    public double getRelativeWidth() {
        return mRelativeWidth;
    }

    /**
     * Sets the relative width.
     *
     * @param pRelativeWidth the relative width to set to
     */
    public void setRelativeWidth(double pRelativeWidth) {
        mRelativeWidth = pRelativeWidth;
    }

    /**
     * @return the relative height
     */
    public double getRelativeHeight() {
        return mRelativeHeight;
    }

    /**
     * Sets the relative height.
     *
     * @param pRelativeHeight the relative height to set to
     */
    public void setRelativeHeight(double pRelativeHeight) {
        mRelativeHeight = pRelativeHeight;
    }

    /**
     * @return the relative x offset
     */
    public double getRelativeOffsetX() {
        return mRelativeOffsetX;
    }

    /**
     * Sets the relative x offset.
     *
     * @param pRelativeOffsetX the relative x offset to set to
     */
    public void setRelativeOffsetX(double pRelativeOffsetX) {
        mRelativeOffsetX = pRelativeOffsetX;
    }

    /**
     * @return the relative y offset
     */
    public double getRelativeOffsetY() {
        return mRelativeOffsetY;
    }

    /**
     * Sets the relative y offset.
     *
     * @param pRelativeOffsetY the relative y offset to set to
     */
    public void setRelativeOffsetY(double pRelativeOffsetY) {
        mRelativeOffsetY = pRelativeOffsetY;
    }

}
