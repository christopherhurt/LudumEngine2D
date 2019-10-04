//package main;
//
//import org.lwjgl.openal.AL10;
//import org.lwjgl.util.WaveData;
//
//@Deprecated
//public final class Sound {
//
//    private String id;
//    private int soundID;
//    private int sourceID;
//    private boolean looping;
//
//    public Sound(String id, String soundFile, boolean looping){
//        if(!SoundBank.getInitialized()){
//            SoundBank.init();
//        }
//
//        this.id = id;
//        this.looping = looping;
//
//        soundID = AL10.alGenBuffers();
//        WaveData waveSound = WaveData.create(soundFile);
//        AL10.alBufferData(soundID, waveSound.format, waveSound.data, waveSound.samplerate);
//        waveSound.dispose();
//
//        sourceID = AL10.alGenSources();
//        AL10.alSource3f(sourceID, AL10.AL_POSITION, 0, 0, 0);
//        AL10.alSource3f(sourceID, AL10.AL_VELOCITY, 0, 0, 0);
//        AL10.alSourcei(sourceID, AL10.AL_LOOPING, looping ? AL10.AL_TRUE : AL10.AL_FALSE);
//
//        SoundBank.addSound(this);
//    }
//
//    public void play(){
//        stop();
//        AL10.alSourcei(sourceID, AL10.AL_BUFFER, soundID);
//        continuePlaying();
//    }
//
//    public void continuePlaying(){
//        AL10.alSourcePlay(sourceID);
//    }
//
//    public void pause(){
//        AL10.alSourcePause(sourceID);
//    }
//
//    public void stop(){
//        AL10.alSourceStop(sourceID);
//    }
//
//    public void setVolume(float volume){
//        AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
//    }
//
//    public boolean isPlaying(){
//        return AL10.alGetSourcei(sourceID, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
//    }
//
//    public String getID(){
//        return id;
//    }
//
//    protected int getSoundID(){
//        return soundID;
//    }
//
//    protected int getSourceID(){
//        return sourceID;
//    }
//
//    protected boolean getLooping(){
//        return looping;
//    }
//
//}
