package ludumEngine2D;

/**
 * Fired when an event occurs regarding a particular interpolation, e.g. when the interpolation is finished. While
 * interpolations are static across all scenes, these events are fired only for objects in the current scene.
 *
 * @author Chris Hurt
 * @version 10.05.19
 */
public final class InterpolationEvent extends AEvent {

    private InterpolatedDouble mInterpolation;

    /**
     * Package-private constructor.
     *
     * @param pType the event type enum
     * @param pScene the current scene at the time this event was fired
     * @param pInterpolation the interpolation associated with this event
     */
    InterpolationEvent(EventType pType, Scene pScene, InterpolatedDouble pInterpolation) {
        super(pType, pScene);
        mInterpolation = pInterpolation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isValidEventType(EventType pType) {
        return pType == EventType.INTERPOLATION_FINISHED;
    }

    /**
     * @return the interpolation associated with this event
     */
    public InterpolatedDouble getInterpolation() {
        return mInterpolation;
    }

}
