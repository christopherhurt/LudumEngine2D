package main;

/**
 * Represents a game event. This includes frame updates, scene changes, scene modifications, game start/stop, etc.
 *
 * @author Chris Hurt
 * @version 10.04.19
 */
public final class GameEvent extends AEvent {

    /**
     * Constructor.
     *
     * @param pType the event type
     * @param pScene the scene in which the event was fired
     */
    GameEvent(EventType pType, Scene pScene) {
        super(pType, pScene);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    boolean isValidEventType(EventType pType) {
        return pType == EventType.GAME_START
                || pType == EventType.GAME_CLOSE
                || pType == EventType.SCENE_LOAD
                || pType == EventType.SCENE_UNLOAD
                || pType == EventType.ADD_TO_SCENE
                || pType == EventType.REMOVE_FROM_SCENE
                || pType == EventType.UPDATE
                || pType == EventType.LATE_UPDATE;
    }

}
