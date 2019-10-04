package main;

/**
 * Represents a transform describing a game object's position and size.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class Transform {

    private double mX;
    private double mY;
    private double mWidth;
    private double mHeight;

    /**
     * Default constructor.
     */
    public Transform() {
        this(0d, 0d, 0.1, 0.1);
    }

    /**
     * Constructor.
     *
     * @param pX the normalized x position relative to the height of the window
     * @param pY the normalized y position relative to the height of the window
     * @param pWidth the normalized width relative to the height of the window
     * @param pHeight the normalized height relative to the height of the window
     */
    public Transform(double pX, double pY, double pWidth, double pHeight) {
        mX = pX;
        mY = pY;
        mWidth = pWidth;
        mHeight = pHeight;
    }

    /**
     * @return the x position
     */
    public double getX() {
        return mX;
    }

    /**
     * Sets the x position.
     *
     * @param pX the x position to be set to
     */
    public void setX(double pX) {
        mX = pX;
    }

    /**
     * @return the y position
     */
    public double getY() {
        return mY;
    }

    /**
     * Sets the y position.
     *
     * @param pY the y position to be set to
     */
    public void setY(double pY) {
        mY = pY;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return mWidth;
    }

    /**
     * Sets the width.
     *
     * @param pWidth the width to be set to
     */
    public void setWidth(double pWidth) {
        mWidth = pWidth;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return mHeight;
    }

    /**
     * Sets the height.
     *
     * @param pHeight the height to be set to
     */
    public void setHeight(double pHeight) {
        mHeight = pHeight;
    }

}