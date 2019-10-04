package main;

/**
 * Represents a kinematics component that controls a game object's linear velocity and acceleration.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class Kinematics {

    private double mVelX;
    private double mVelY;
    private double mAccX;
    private double mAccY;

    /**
     * Default constructor.
     */
    public Kinematics() {
        this(0d, 0d, 0d, 0d);
    }

    /**
     * Constructor. All distance units are equivalent to those in {@link Transform}, and time values are in seconds.
     *
     * @param pVelX the x velocity
     * @param pVelY the y velocity
     * @param pAccX the x acceleration
     * @param pAccY the y acceleration
     */
    public Kinematics(double pVelX, double pVelY, double pAccX, double pAccY) {
        mVelX = pVelX;
        mVelY = pVelY;
        mAccX = pAccX;
        mAccY = pAccY;
    }

    /**
     * Updates the velocities of this kinematics component, as well as the position of the given transform.
     *
     * @param pTransform the transform to update with this kinematics component
     */
    void update(Transform pTransform) {
        mVelX += mAccX * Time.getDelta();
        mVelY += mAccY * Time.getDelta();

        pTransform.setX(pTransform.getX() + mVelX * Time.getDelta()
                + mAccX * 0.5 * Time.getDelta() * Time.getDelta());
        pTransform.setY(pTransform.getY() + mVelY * Time.getDelta()
                + mAccY * 0.5 * Time.getDelta() * Time.getDelta());
    }

    /**
     * @return the x velocity
     */
    public double getVelX() {
        return mVelX;
    }

    /**
     * Sets the x velocity.
     *
     * @param pVelX the x velocity to be set to
     */
    public void setVelX(double pVelX) {
        mVelX = pVelX;
    }

    /**
     * @return the y velocity
     */
    public double getVelY() {
        return mVelY;
    }

    /**
     * Sets the y velocity.
     *
     * @param pVelY the y velocity to be set to
     */
    public void setVelY(double pVelY) {
        mVelY = pVelY;
    }

    /**
     * @return the x acceleration
     */
    public double getAccX() {
        return mAccX;
    }

    /**
     * Sets the x acceleration
     *
     * @param pAccX the x acceleration to be set to
     */
    public void setAccX(double pAccX) {
        mAccX = pAccX;
    }

    /**
     * @return the y acceleration
     */
    public double getAccY() {
        return mAccY;
    }

    /**
     * Sets the y acceleration.
     *
     * @param pAccY the y acceleration to be set to
     */
    public void setAccY(double pAccY) {
        mAccY = pAccY;
    }

}
