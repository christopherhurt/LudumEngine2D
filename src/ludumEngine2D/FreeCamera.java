package ludumEngine2D;

/**
 * A camera implementation that moves freely in the scene. This camera can be assigned a velocity that can be applied
 * to the camera's position.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class FreeCamera extends ACamera {

    private double mVelX;
    private double mVelY;

    /**
     * Default constructor.
     */
    public FreeCamera() {
        this(0d, 0d, 0d, 0d);
    }

    /**
     * Constructor.
     *
     * @param pX the initial x position
     * @param pY the initial y position
     * @param pVelX the initial x velocity
     * @param pVelY the initial y velocity
     */
    public FreeCamera(double pX, double pY, double pVelX, double pVelY) {
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
    public double getVelX() {
        return mVelX;
    }

    /**
     * @return the y velocity
     */
    public double getVelY() {
        return mVelY;
    }

    /**
     * Sets the x velocity.
     *
     * @param pVelX the new x velocity
     */
    public void setVelX(double pVelX) {
        mVelX = pVelX;
    }

    /**
     * Sets the y velocity.
     *
     * @param pVelY the new y velocity
     */
    public void setVelY(double pVelY) {
        mVelY = pVelY;
    }

}
