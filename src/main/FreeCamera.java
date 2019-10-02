package main;

/**
 * A camera implementation that moves freely in the scene. This camera can be assigned a velocity that can be applied
 * to the camera's position.
 *
 * @author Chris Hurt
 * @version 10.01.19
 */
public final class FreeCamera
        implements ICamera {

    private float mX;
    private float mY;
    private float mVelX;
    private float mVelY;

    /**
     * Default constructor.
     */
    public FreeCamera() {
        this(0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Constructor.
     *
     * @param pX the initial x position
     * @param pY the initial y position
     * @param pVelX the initial x velocity
     * @param pVelY the initial y velocity
     */
    public FreeCamera(float pX, float pY, float pVelX, float pVelY) {
        mX = pX;
        mY = pY;
        mVelX = pVelX;
        mVelY = pVelY;
    }

    /**
     * Updates the position of the camera.
     */
    void update() {
        mX += mVelX * Time.getDelta();
        mY += mVelY * Time.getDelta();
    }

    /**
     * @return the x velocity
     */
    public float getVelX() {
        return mVelX;
    }

    /**
     * @return the y velocity
     */
    public float getVelY() {
        return mVelY;
    }

    /**
     * Sets the x velocity.
     *
     * @param pVelX the new x velocity
     */
    public void setVelX(float pVelX) {
        mVelX = pVelX;
    }

    /**
     * Sets the y velocity.
     *
     * @param pVelY the new y velocity
     */
    public void setVelY(float pVelY) {
        mVelY = pVelY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getX() {
        return mX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getY() {
        return mY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(float pX) {
        mX = pX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(float pY) {
        mY = pY;
    }

}
