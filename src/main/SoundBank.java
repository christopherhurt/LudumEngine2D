//package main;
//
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//import org.lwjgl.LWJGLException;
//import org.lwjgl.openal.AL;
//import org.lwjgl.openal.AL10;
//
//@Deprecated
//public class SoundBank {
//
//    private static List<Sound> bank = new CopyOnWriteArrayList<>();
//    private static boolean initialized = false;
//
//    private SoundBank(){}
//
//    public static Sound getSound(String id){
//        for(Sound s : bank){
//            if(s.getID().equals(id)){
//                return s;
//            }
//        }
//        throw new IllegalArgumentException("No sound with the given ID was found");
//    }
//
//    protected static void init(){
//        if(!initialized){
//            try{
//                AL.create();
//            }catch(LWJGLException e){
//                e.printStackTrace();
//            }
//
//            AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
//            AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
//            initialized = true;
//        }else{
//            throw new IllegalStateException("Audio already initialized");
//        }
//    }
//
//    protected static void addSound(Sound sound){
//        for(Sound s : bank){
//            if(s.getID().equals(sound.getID())){
//                throw new IllegalStateException("Conflicting sound IDs");
//            }
//        }
//        bank.add(sound);
//    }
//
//    protected static boolean getInitialized(){
//        return initialized;
//    }
//
//    public static void stopAll(){
//        for(Sound sound : bank){
//            sound.stop();
//        }
//    }
//
//    public static void setVolume(float volume, boolean forLooping){
//        for(Sound sound : bank){
//            if(sound.getLooping() == forLooping){
//                sound.setVolume(volume);
//            }
//        }
//    }
//
//    public static void deleteAll(){
//        for(Sound sound : bank){
//            sound.stop();
//            AL10.alDeleteSources(sound.getSourceID());
//            AL10.alDeleteBuffers(sound.getSoundID());
//        }
//
//        AL.destroy();
//    }
//
//}
