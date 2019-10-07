package ludumEngine2D;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;

import java.util.Collection;
import java.util.HashSet;

/**
 * Factory class used to create new Sound objects. These sounds can only be created here so they can be registered and
 * properly deleted when the game is terminated. This class is also responsible for launching and terminating the sound
 * engine on application launch and close.
 *
 * @author Chris Hurt
 * @version 10.05.19
 */
public final class SoundFactory {

    /*
     * OpenAL initializations and configurations. Note that the default audio model is used.
     */
    static {
        try {
            AL.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        AL10.alListener3f(AL10.AL_POSITION, 0.0f, 0.0f, 0.0f);
        AL10.alListener3f(AL10.AL_VELOCITY, 0.0f, 0.0f, 0.0f);
    }

    /** The collection of registered Sound objects */
    private static Collection<Sound> SOUNDS = new HashSet<>();

    /**
     * Creates and registers a new Sound object.
     *
     * @param pPath the relative file path of the sound's audio file
     * @param pGain the gain at which to play the sound
     * @param pLooping whether to loop the sound or not when playing it
     * @return the new Sound object
     */
    public static Sound createSound(String pPath, float pGain, boolean pLooping) {
        Sound sound = new Sound(pPath, pGain, pLooping);
        SOUNDS.add(sound);
        return sound;
    }

    /**
     * Destroys the sound engine and all registered sounds/sources.
     */
    static void destroyAll() {
        SOUNDS.forEach(Sound::destroy);
        AL.destroy();
    }

    private SoundFactory() {
        // Static class, do not initialize
    }

}
