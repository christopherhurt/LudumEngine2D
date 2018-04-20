package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game {
    
    private static boolean initialized = false;
    private static Canvas canvas = null;
    private static float oneOverFps = 0;
    private static Color clearColor = null;
    private static boolean gamePaused = false;
    private static String resourcesFolder = "res";
    private static List<Scene> scenes = new CopyOnWriteArrayList<>();
    private static Scene currentScene = null;
    
    private Game(){}
    
    public static void start(int windowWidth, int windowHeight, String windowTitle, String windowIconFile, int fpsCap, Color clearColor){
        if(initialized){
            throw new IllegalStateException("Game has already been initialized");
        }
        initialized = true;
        
        if(currentScene == null) {
            throw new IllegalStateException("You must create a scene before starting the game");
        }
        
        canvas = new Canvas();
        Input inputListener = new Input();
        canvas.addKeyListener(inputListener);
        canvas.addMouseListener(inputListener);
        Window.init(windowWidth, windowHeight, windowTitle, windowIconFile, canvas);
        
        oneOverFps = 1.0f / fpsCap;
        Game.clearColor = clearColor;
        
        begin();
    }
    
    public static boolean createScene(String sceneID) {
        for(Scene scene : scenes) {
            if(scene.getID().equals(sceneID)) {
                return false;
            }
        }
        
        Scene newScene = new Scene(sceneID);
        boolean success = scenes.add(newScene);
        
        if(currentScene == null && success) {
            currentScene = newScene;
        }
        
        return success;
    }
    
    public static boolean deleteScene(String sceneID) {
        for(Scene scene : scenes) {
            if(scene.getID().equals(sceneID)) {
                if(currentScene == scene) {
                    return false;
                }
                
                return scenes.remove(scene);
            }
        }
        
        return false;
    }
    
    public static boolean addObjectToScene(String sceneID, GameObject obj) {
        for(Scene scene : scenes) {
            if(scene.getID().equals(sceneID)) {
                return scene.addObject(obj);
            }
        }
        
        return false;
    }
    
    public static boolean removeObjectFromScene(String sceneID, GameObject obj) {
        for(Scene scene : scenes) {
            if(scene.getID().equals(sceneID)) {
                return scene.removeObject(obj);
            }
        }
        
        return false;
    }
    
    public static boolean attachMenuToScene(String sceneID, Menu menu) {
        for(Scene scene : scenes) {
            if(scene.getID().equals(sceneID)) {
                scene.attachMenu(menu);
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean detachMenuFromScene(String sceneID) {
        for(Scene scene : scenes) {
            if(scene.getID().equals(sceneID)) {
                scene.detachMenu();
                return true;
            }
        }
        
        return false;
    }
    
    public static void clearScene(String sceneID) {
        for(Scene scene : scenes) {
            if(scene.getID().equals(sceneID)) {
                scene.clearObjects();
            }
        }
    }
    
    public static List<GameObject> getGameObjects(String objID) {
        return currentScene.getObjects(objID);
    }
    
    public static Menu getMenu() {
        return currentScene.getMenu();
    }
    
    public static void readdObjects(String objID) {
        currentScene.readdObjects(objID);
    }
    
    public static int getObjectsCount() {
        return currentScene.getObjectCount();
    }
    
    public static boolean setCurrentScene(String sceneID) {
        if(sceneID == null) {
            return false;
        }
        
        for(Scene scene : scenes) {
            if(scene.getID().equals(sceneID)) {
                currentScene = scene;
                return true;
            }
        }
        
        return false;
    }
    
    protected static void updateFonts() {
        for(Scene scene : scenes) {
            scene.updateMenuFonts();
        }
    }
    
    public static void setPaused(boolean paused){
        gamePaused = paused;
    }
    
    public static boolean isPaused(){
        return gamePaused;
    }
    
    public static void setWindowSize(int width, int height){
        Window.setSize(width, height);
    }
    
    public static Dimension getWindowSize(){
        return new Dimension(Window.getWidth(), Window.getHeight());
    }
    
    public static float getAspectRatio() {
        return (float)Window.getWidth() / Window.getHeight();
    }
    
    public static Point getMouseLocation(){
        return canvas.getMousePosition();
    }
    
    private static void begin(){
        canvas.requestFocus();
        
        new Thread("Game"){
            @Override
            public void run(){
                long lastTime;
                long currTime = System.nanoTime();
                double delta = 0;
                
                while(true){
                    lastTime = currTime;
                    currTime = System.nanoTime();
                    
                    delta += (currTime - lastTime) / 1000000000.0;
                    
                    if(delta >= oneOverFps){
                        update();
                        render();
                        delta -= oneOverFps;
                    }
                }
            }
        }.start();
    }
    
    private static void update(){
        Input.update();
        if(!gamePaused){
            LinearInterpolator.update();
            Camera.update();
        }
        currentScene.update();
    }
    
    private static void render(){
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs == null){
            canvas.createBufferStrategy(3);
            return;
        }
        
        Graphics2D g = (Graphics2D)bs.getDrawGraphics();
        
        g.setColor(clearColor);
        g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
        
        currentScene.render(g);
        
        g.dispose();
        bs.show();
    }
    
    protected static float getOneOverFps(){
        return oneOverFps;
    }
    
    protected static boolean isInitialized(){
        return initialized;
    }
    
    protected static String getResourcesFolder(){
        return resourcesFolder;
    }
    
    public static void setResourcesFolder(String resFolder){
        resourcesFolder = resFolder;
    }
    
}
