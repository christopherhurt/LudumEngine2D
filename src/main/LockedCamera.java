package main;

/**
 * A camera implementation that can be locked to a game object. The camera will automatically be repositioned over
 * the game object as it moves.
 *
 * @author Chris Hurt
 * @version 10.01.19
 */
public final class LockedCamera
        implements ICamera {

    private float mX = 0.0f;
    private float mY = 0.0f;

    private float mOffsetX;
    private float mOffsetY;
    private GameObject mTarget;

    /**
     * Default constructor.
     */
    public LockedCamera() {
        this(null);
    }

    /**
     * Constructor.
     *
     * @param pTarget the game object to lock to
     */
    public LockedCamera(GameObject pTarget) {
        this(pTarget, 0.0f, 0.0f);
    }

    /**
     * Default constructor.
     *
     * @param pTarget the game object to lock to
     * @param pOffsetX the x offset of the camera from the game object
     * @param pOffsetY the y offset of the camera from the game object
     */
    public LockedCamera(GameObject pTarget, float pOffsetX, float pOffsetY) {
        mTarget = pTarget;
        mOffsetX = pOffsetX;
        mOffsetY = pOffsetY;
    }

    /**
     * Updates the position of the camera to the target's if one is assigned.
     */
    void update() {
        if (mTarget != null) {
            mX = mTarget.getX() + mTarget.getWidth() / 2.0f + mOffsetX;
            mY = mTarget.getY() + mTarget.getHeight() / 2.0f + mOffsetY;
        }
    }

    /**
     * Sets the target game object to lock to.
     *
     * @param pTarget the game object to lock to
     */
    public void setTarget(GameObject pTarget) {
        mTarget = pTarget;
    }

    /**
     * Unlocks the camera from the currently-targeted game object.
     */
    public void unlock() {
        mTarget = null;
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
