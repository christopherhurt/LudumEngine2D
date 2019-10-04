package main;

/**
 * Camera class used for controlling player perspective.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public abstract class ACamera {

    private double mX;
    private double mY;

    /**
     * Constructor.
     *
     * @param pX the initial x position of the camera
     * @param pY the initial y position of the camera
     */
    ACamera(double pX, double pY) {
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
    public final double getX() {
        return mX;
    }

    /**
     * @return the y position
     */
    public final double getY() {
        return mY;
    }

    /**
     * Sets the x position.
     *
     * @param pX the new x position
     */
    public final void setX(double pX) {
        mX = pX;
    }

    /**
     * Sets the y position.
     *
     * @param pY the new y position
     */
    public final void setY(double pY) {
        mY = pY;
    }

}
