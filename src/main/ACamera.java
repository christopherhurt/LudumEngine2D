package main;

/**
 * Camera class used for controlling player perspective.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public abstract class ACamera {

    private float mX;
    private float mY;

    /**
     * Constructor.
     *
     * @param pX the initial x position of the camera
     * @param pY the initial y position of the camera
     */
    ACamera(float pX, float pY) {
        mX = pX;
        mY = pY;
    }

    /**
     * Updates the position of the camera.
     */
    abstract void update();

    /**
     * @return the x position
     */
    public final float getX() {
        return mX;
    }

    /**
     * @return the y position
     */
    public final float getY() {
        return mY;
    }

    /**
     * Sets the x position.
     *
     * @param pX the new x position
     */
    public final void setX(float pX) {
        mX = pX;
    }

    /**
     * Sets the y position.
     *
     * @param pY the new y position
     */
    public final void setY(float pY) {
        mY = pY;
    }

}
