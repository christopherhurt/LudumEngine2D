package main;

import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

/**
 * Represents a sound that can be played, paused, and stopped at any point by the user of the engine. The sound's gain
 * and whether it should loop when played are configurable.
 *
 * NOTE - These sounds can only be loaded from audio files in the .wav format!
 *
 * @author Chris Hurt
 * @version 10.05.19
 */
public final class Sound {

    /**
     * The OpenAL z-axis offset of the sound's source. This should be small but non-zero to prevent strange effects
     * that occur when playing a sound at the origin point for a listener located at the origin point.
     */
    private static final float SOURCE_Z_OFFSET = 0.001f;

    private int mId;
    private int mSource;

    /**
     * Package-private constructor.
     *
     * @param pPath the relative file path at which the sound's audio file is located
     * @param pGain the gain at which to play the sound
     * @param pLooping whether or not to loop the sound when playing it
     */
    Sound(String pPath, float pGain, boolean pLooping) {
        // Create sound data buffer
        mId = AL10.alGenBuffers();
        WaveData waveSound = WaveData.create(pPath);
        AL10.alBufferData(mId, waveSound.format, waveSound.data, waveSound.samplerate);
        waveSound.dispose();

        // Create sound source
        mSource = AL10.alGenSources();
        AL10.alSourcei(mSource, AL10.AL_BUFFER, mId);
        AL10.alSource3f(mSource, AL10.AL_POSITION, 0.0f, 0.0f, SOURCE_Z_OFFSET);
        AL10.alSource3f(mSource, AL10.AL_VELOCITY, 0.0f, 0.0f, 0.0f);
        setGain(pGain);
        setLooping(pLooping);
    }

    /**
     * Plays the sound.
     */
    public void play() {
        AL10.alSourcePlay(mSource);
    }

    /**
     * Pauses the sound.
     */
    public void pause() {
        AL10.alSourcePause(mSource);
    }

    /**
     * Stops the sound.
     */
    public void stop() {
        AL10.alSourceStop(mSource);
    }

    /**
     * @return whether the sound is currently playing
     */
    public boolean isPlaying() {
        return AL10.alGetSourcei(mSource, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
    }

    /**
     * @return the gain at which the sound is playing
     */
    public float getGain() {
        return AL10.alGetSourcef(mSource, AL10.AL_GAIN);
    }

    /**
     * Sets the gain at which the sound should play.
     *
     * @param pGain the gain
     */
    public void setGain(float pGain) {
        AL10.alSourcef(mSource, AL10.AL_GAIN, pGain);
    }

    /**
     * @return whether the sound loops when played
     */
    public boolean getLooping() {
        return AL10.alGetSourcei(mSource, AL10.AL_LOOPING) == AL10.AL_TRUE;
    }

    /**
     * Sets whether the sound should loop when played.
     *
     * @param pLooping whether to loop the sound
     */
    public void setLooping(boolean pLooping) {
        AL10.alSourcei(mSource, AL10.AL_LOOPING, pLooping ? AL10.AL_TRUE : AL10.AL_FALSE);
    }

    /**
     * Destroys this sound and the resources it uses, includes its audio data buffer and source.
     */
    void destroy() {
        stop();
        AL10.alDeleteBuffers(mId);
        AL10.alDeleteSources(mSource);
    }

}
