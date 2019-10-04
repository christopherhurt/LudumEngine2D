package main;

/**
 * A camera implementation that moves freely in the scene. This camera can be assigned a velocity that can be applied
 * to the camera's position.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class FreeCamera extends ACamera {

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
        super(pX, pY);
        mVelX = pVelX;
        mVelY = pVelY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void update() {
        setX(getX() + mVelX * Time.getDelta());
        setY(getY() + mVelY * Time.getDelta());
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

}
