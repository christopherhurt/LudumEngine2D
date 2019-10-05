package main;

/**
 * Represents a double value that can be interpolated. Interpolation is handled by the engine and progress can monitored
 * by the user of the engine. The user can also start/stop the interpolation whenever they want. An
 * INTERPOLATION_FINISHED event will be fired for all game objects in the current scene when the interpolation has
 * reached its final value.
 *
 * @author Chris Hurt
 * @version 10.05.19
 */
public final class InterpolatedDouble {

    private String mName;
    private double mInitialValue;
    private double mFinalValue;
    private double mDuration;

    private double mValue;
    private double mTimestep;
    private boolean mRunning = false;

    /**
     * Package-private constructor.
     *
     * @param pName the identifying name of this interpolation
     * @param pInitialValue the initial value to interpolate from
     * @param pFinalValue the final value to interpolate to
     * @param pDuration the duration of the interpolation in seconds
     */
    InterpolatedDouble(String pName, double pInitialValue, double pFinalValue, double pDuration) {
        mName = pName;
        mInitialValue = pInitialValue;
        mFinalValue = pFinalValue;
        mDuration = pDuration;

        mValue = mInitialValue;
        mTimestep = (mFinalValue - mInitialValue) / mDuration;
    }

    /**
     * Updates the value of this interpolation, called once per frame, and checks whether it's reached its final value.
     */
    void update() {
        if (mRunning && !isFinished()) {
            mValue += mTimestep * Time.getDelta();

            if (isFinished()) {
                mValue = mFinalValue; // Clamp to final value
                mRunning = false;
                new InterpolationEvent(EventType.INTERPOLATION_FINISHED, Game.getCurrentScene(), this)
                        .fire(Game.getCurrentScene().getCopyOfObjects());
            }
        }
    }

    /**
     * @return whether this interpolation is currently being interpolated
     */
    public boolean isRunning() {
        return mRunning;
    }

    /**
     * Sets whether this interpolation is currently being interpolated.
     *
     * @param pRunning whether to interpolate
     */
    public void setRunning(boolean pRunning) {
        if (pRunning && isFinished()) {
            Debug.error("Tried to run interpolation with name "
                    + mName + " that is already finished, call reset() first");
        } else {
            mRunning = pRunning;
        }
    }

    /**
     * @return whether this interpolation has reached its final value
     */
    public boolean isFinished() {
        return mValue >= mFinalValue;
    }

    /**
     * Resets this interpolation's value to its initial value.
     *
     * NOTE - This does not start the interpolation again if it was previously not running.
     */
    public void reset() {
        mValue = mInitialValue;
    }

    /**
     * @return the current interpolated value
     */
    public double getValue() {
        return mValue;
    }

    /**
     * @return the identifying name
     */
    public String getName() {
        return mName;
    }

    /**
     * @return the initial value to interpolate from
     */
    public double getInitialValue() {
        return mInitialValue;
    }

    /**
     * @return the final value to interpolate to
     */
    public double getFinalValue() {
        return mFinalValue;
    }

    /**
     * @return the duration of the interpolation in seconds
     */
    public double getDuration() {
        return mDuration;
    }

}
