package main;

import java.util.List;

/**
 * Abstract event class used to represent every type of event.
 *
 * @author Chris Hurt
 * @version 10.04.19
 */
public abstract class AEvent {

    private EventType mType;
    private Scene mScene;

    /**
     * Constructor.
     *
     * @param pType the event type enum
     * @param pScene the scene in which the event occurred
     */
    AEvent(EventType pType, Scene pScene) {
        mType = pType;
        mScene = pScene;

        if (!isValidEventType(mType)) {
            throw new IllegalArgumentException("Invalid event type "
                    + mType.getStringRepresentation() + " for event object type " + getClass().getSimpleName());
        }
    }

    /**
     * Fires this event to be handled by the specified game object targets.
     *
     * @param pTargets the list of targets
     */
    final void fire(List<GameObject> pTargets) {
        fireForGameObjects(pTargets);
    }

    /**
     * Recursive helper method that calls the handler for each of the specified targets, as well as their descendants.
     *
     * @param pTargets the list of targets of this event
     */
    private void fireForGameObjects(List<GameObject> pTargets) {
        pTargets.forEach(target -> {
            target.getHandler().ifPresent(handler -> handler.handle(this, target));
            fireForGameObjects(target.getChildren());
        });
    }

    /**
     * Checks if the specified event type enum is valid for this event object type.
     *
     * @param pType the event type enum to check
     * @return whether the event type is valid
     */
    abstract boolean isValidEventType(EventType pType);

    /**
     * @return the event type enum
     */
    public final EventType getType() {
        return mType;
    }

    /**
     * @return the scene in which this event occurred
     */
    public final Scene getScene() {
        return mScene;
    }

}
