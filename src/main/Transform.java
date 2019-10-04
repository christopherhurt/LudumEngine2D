package main;

/**
 * Represents a transform describing a game object's position, rotation, and scale.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class Transform {

    private float mX;
    private float mY;
    private float mWidth;
    private float mHeight;
    private float mRotation;

    /**
     * Default constructor.
     */
    public Transform() {
        this(0.0f, 0.0f, 0.1f, 0.1f, 0.0f);
    }

    /**
     * Constructor.
     *
     * @param pX the normalized x position relative to the height of the window
     * @param pY the normalized y position relative to the height of the window
     * @param pWidth the normalized width relative to the height of the window
     * @param pHeight the normalized height relative to the height of the window
     * @param pRotation the rotation in degrees
     */
    public Transform(float pX, float pY, float pWidth, float pHeight, float pRotation) {
        mX = pX;
        mY = pY;
        mWidth = pWidth;
        mHeight = pHeight;
        mRotation = pRotation;
    }

    /**
     * @return the x position
     */
    public float getX() {
        return mX;
    }

    /**
     * Sets the x position.
     *
     * @param pX the x position to be set to
     */
    public void setX(float pX) {
        mX = pX;
    }

    /**
     * @return the y position
     */
    public float getY() {
        return mY;
    }

    /**
     * Sets the y position.
     *
     * @param pY the y position to be set to
     */
    public void setY(float pY) {
        mY = pY;
    }

    /**
     * @return the width
     */
    public float getWidth() {
        return mWidth;
    }

    /**
     * Sets the width.
     *
     * @param pWidth the width to be set to
     */
    public void setWidth(float pWidth) {
        mWidth = pWidth;
    }

    /**
     * @return the height
     */
    public float getHeight() {
        return mHeight;
    }

    /**
     * Sets the height.
     *
     * @param pHeight the height to be set to
     */
    public void setHeight(float pHeight) {
        mHeight = pHeight;
    }

    /**
     * @return the rotation
     */
    public float getRotation() {
        return mRotation;
    }

    /**
     * Sets the rotation.
     *
     * @param pRotation the rotation to be set to
     */
    public void setRotation(float pRotation) {
        mRotation = pRotation;
    }

}
