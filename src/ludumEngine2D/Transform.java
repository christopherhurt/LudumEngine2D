package ludumEngine2D;

/**
 * Represents a transform describing a game object's position and scale.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class Transform {

    private double mX;
    private double mY;
    private double mScaleX;
    private double mScaleY;

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
     * @param pScaleX the normalized x scale relative to the height of the window
     * @param pScaleY the normalized y scale relative to the height of the window
     */
    public Transform(double pX, double pY, double pScaleX, double pScaleY) {
        mX = pX;
        mY = pY;
        mScaleX = pScaleX;
        mScaleY = pScaleY;
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
     * @return the x scale
     */
    public double getScaleX() {
        return mScaleX;
    }

    /**
     * Sets the x scale.
     *
     * @param pScaleX the x scale to be set to
     */
    public void setScaleX(double pScaleX) {
        mScaleX = pScaleX;
    }

    /**
     * @return the y scale
     */
    public double getScaleY() {
        return mScaleY;
    }

    /**
     * Sets the y scale.
     *
     * @param pScaleY the y scale to be set to
     */
    public void setScaleY(double pScaleY) {
        mScaleY = pScaleY;
    }

    /**
     * @return a copy of this transform
     */
    public Transform copy() {
        return new Transform(mX, mY, mScaleX, mScaleY);
    }

}
