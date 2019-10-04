package main;

/**
 * A camera implementation that can be locked to a game object. The camera will automatically be repositioned over
 * the game object as it moves.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class LockedCamera extends ACamera {

    private double mOffsetX;
    private double mOffsetY;
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
        this(pTarget, 0d, 0d);
    }

    /**
     * Constructor.
     *
     * @param pTarget the game object to lock to
     * @param pOffsetX the x offset of the camera from the game object
     * @param pOffsetY the y offset of the camera from the game object
     */
    public LockedCamera(GameObject pTarget, double pOffsetX, double pOffsetY) {
        super(0d, 0d);
        mTarget = pTarget;
        mOffsetX = pOffsetX;
        mOffsetY = pOffsetY;
    }

    /**
     * Constructor.
     *
     * @param pX the initial x position of the camera
     * @param pY the initial y position of the camera
     */
    public LockedCamera(double pX, double pY) {
        this(pX, pY, 0d, 0d);
    }

    /**
     * Constructor.
     *
     * @param pX the initial x position of the camera
     * @param pY the initial y position of the camera
     * @param pOffsetX the x offset of the camera from the target
     * @param pOffsetY the y offset of the camera from the target
     */
    public LockedCamera(double pX, double pY, double pOffsetX, double pOffsetY) {
        super(pX, pY);
        mOffsetX = pOffsetX;
        mOffsetY = pOffsetY;
        mTarget = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void update() {
        if (mTarget != null) {
            if (mTarget.getTransform().isPresent()) {
                Transform transform = mTarget.getTransform().get();
                // TODO: fix glitchiness here
//                setX(transform.getX() + transform.getWidth() / 2.0f + mOffsetX - 0.5f * Window.getAspectRatio());
//                setY(transform.getY() + transform.getHeight() / 2.0f + mOffsetY - 0.5f);
            } else {
                Debug.warn("Camera is locked to GameObject with id "
                        + mTarget.getId() + " that does not have a Transform component");
            }
        }
    }

    /**
     * @return the game object currently being targeted by the camera
     */
    public GameObject getTarget() {
        return mTarget;
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
     * @return the x offset of the camera from the target
     */
    public double getOffsetX() {
        return mOffsetX;
    }

    /**
     * Sets the x offset of the camera from the target.
     *
     * @param pOffsetX the x offset to be set to
     */
    public void setOffsetX(double pOffsetX) {
        mOffsetX = pOffsetX;
    }

    /**
     * @return the y offset of the camera from the target
     */
    public double getOffsetY() {
        return mOffsetY;
    }

    /**
     * Sets the y offset of the camera from the target.
     *
     * @param pOffsetY the y offset to be set to
     */
    public void setOffsetY(double pOffsetY) {
        mOffsetY = pOffsetY;
    }

}
