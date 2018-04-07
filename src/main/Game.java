package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferStrategy;

public class Game {
    
    private static boolean initialized = false;
    private static Canvas canvas = null;
    private static float oneOverFps = 0;
    private static Color clearColor = null;
    private static boolean gamePaused = false;
    private static String resourcesFolder = "res";
    
    private Game(){}
    
    public static void start(int windowWidth, int windowHeight, String windowTitle, String windowIconFile, int fpsCap, Color clearColor){
        if(initialized){
            throw new IllegalStateException("Game has already been initialized");
        }
        initialized = true;
        
        canvas = new Canvas();
        Input inputListener = new Input();
        canvas.addKeyListener(inputListener);
        canvas.addMouseListener(inputListener);
        Window.init(windowWidth, windowHeight, windowTitle, windowIconFile, canvas);
        
        oneOverFps = 1.0f / fpsCap;
        Game.clearColor = clearColor;
        
        begin();
    }
    
    public static Manager getManager(String name){
        return ManagerHandler.getManager(name);
    }
    
    public static Menu getMenu(String name){
        return MenuHandler.getMenu(name);
    }
    
    public static void setCurrentMenu(String name){
        MenuHandler.setCurrentMenu(name);
    }
    
    public static void setCurrentManager(String name){
        ManagerHandler.setCurrentManager(name);
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
        ManagerHandler.update();
        MenuHandler.update();
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
        
        ManagerHandler.render(g);
        MenuHandler.render(g);
        
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
