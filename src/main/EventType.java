package main;

/**
 * Represents the type of event that occurred. This is intentionally made separate subclass type events of AEvent, as
 * multiple event types require the same amount of information to be processed, e.g. SCENE_LOAD and SCENE_UNLOAD should
 * only require the scene object that was loaded/unloaded. The subclass types are meant to distinguish between events
 * that require different information, e.g. game object intersection information.
 *
 * @author Chris Hurt
 * @version 10.04.19
 */
public enum EventType {

    // Game events
    GAME_START("GAME_START"),
    GAME_CLOSE("GAME_CLOSE"),
    SCENE_LOAD("SCENE_LOAD"),
    SCENE_UNLOAD("SCENE_UNLOAD"),
    ADD_TO_SCENE("ADD_TO_SCENE"),
    REMOVE_FROM_SCENE("REMOVE_FROM_SCENE"),
    UPDATE("UPDATE"),
    LATE_UPDATE("LATE_UPDATE"),

    // Key events
    KEY_PRESSED("KEY_PRESSED"),
    KEY_RELEASED("KEY_RELEASED"),

    // Mouse events
    MOUSE_BUTTON_PRESSED("MOUSE_BUTTON_PRESSED"),
    MOUSE_BUTTON_RELEASED("MOUSE_BUTTON_RELEASED");

    private String mStringRepresentation;

    /**
     * Enum constructor.
     *
     * @param pStringRepresentation the String representation of the enum for debugging purposes
     */
    EventType(String pStringRepresentation) {
        mStringRepresentation = pStringRepresentation;
    }

    /**
     * @return the String representation of the enum
     */
    public String getStringRepresentation() {
        return mStringRepresentation;
    }

}
