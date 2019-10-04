package main;

/**
 * Represents a kinematics component that controls a game object's linear and angular velocity and acceleration.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class Kinematics {

    private float mVelX;
    private float mVelY;
    private float mAngVel;
    private float mAccX;
    private float mAccY;
    private float mAngAcc;

    /**
     * Default constructor.
     */
    public Kinematics() {
        this(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Constructor. All distance units are equivalent to those in {@link Transform}, and time values are in seconds.
     *
     * @param pVelX the x velocity
     * @param pVelY the y velocity
     * @param pAngVel the angular velocity
     * @param pAccX the x acceleration
     * @param pAccY the y acceleration
     * @param pAngAcc the angular acceleration
     */
    public Kinematics(float pVelX, float pVelY, float pAngVel, float pAccX, float pAccY, float pAngAcc) {
        mVelX = pVelX;
        mVelY = pVelY;
        mAngVel = pAngVel;
        mAccX = pAccX;
        mAccY = pAccY;
        mAngAcc = pAngAcc;
    }

    /**
     * Updates the velocities of this kinematics component, as well as the positions and rotation of the given
     * transform.
     *
     * @param pTransform the transform to update with this kinematics component
     */
    void update(Transform pTransform) {
        mVelX += mAccX * Time.getDelta();
        mVelY += mAccY * Time.getDelta();
        mAngVel += mAngAcc * Time.getDelta();

        pTransform.setX(pTransform.getX() + mVelX * Time.getDelta()
                + mAccX * 0.5f * Time.getDelta() * Time.getDelta());
        pTransform.setY(pTransform.getY() + mVelY * Time.getDelta()
                + mAccY * 0.5f * Time.getDelta() * Time.getDelta());
        pTransform.setRotation(pTransform.getRotation() + mAngVel * Time.getDelta()
                + mAngAcc * 0.5f * Time.getDelta() * Time.getDelta());
    }

    /**
     * @return the x velocity
     */
    public float getVelX() {
        return mVelX;
    }

    /**
     * Sets the x velocity.
     *
     * @param pVelX the x velocity to be set to
     */
    public void setVelX(float pVelX) {
        mVelX = pVelX;
    }

    /**
     * @return the y velocity
     */
    public float getVelY() {
        return mVelY;
    }

    /**
     * Sets the y velocity.
     *
     * @param pVelY the y velocity to be set to
     */
    public void setVelY(float pVelY) {
        mVelY = pVelY;
    }

    /**
     * @return the angular velocity
     */
    public float getAngVel() {
        return mAngVel;
    }

    /**
     * Sets the angular velocity.
     *
     * @param pAngVel the angular velocity to be set to
     */
    public void setAngVel(float pAngVel) {
        mAngVel = pAngVel;
    }

    /**
     * @return the x acceleration
     */
    public float getAccX() {
        return mAccX;
    }

    /**
     * Sets the x acceleration
     *
     * @param pAccX the x acceleration to be set to
     */
    public void setAccX(float pAccX) {
        mAccX = pAccX;
    }

    /**
     * @return the y acceleration
     */
    public float getAccY() {
        return mAccY;
    }

    /**
     * Sets the y acceleration.
     *
     * @param pAccY the y acceleration to be set to
     */
    public void setAccY(float pAccY) {
        mAccY = pAccY;
    }

    /**
     * @return the angular acceleration
     */
    public float getAngAcc() {
        return mAngAcc;
    }

    /**
     * Sets the angular acceleration.
     *
     * @param pAngAcc the angular acceleration to be set to
     */
    public void setAngAcc(float pAngAcc) {
        mAngAcc = pAngAcc;
    }

}
