package main;

public class Camera {
    
    private static float x = 0, y = 0, vX = 0, vY = 0, aX = 0, aY = 0;
    
    private Camera(){};
    
    protected static void update(){
        vX += aX;
        vY += aY;
        x += vX;
        y += vY;
    }
    
    public static float getX(){
        return x;
    }
    
    public static void setX(float x){
        Camera.x = x;
    }
    
    public static float getY(){
        return y;
    }
    
    public static void setY(float y){
        Camera.y = y;
    }
    
    public static float getVelX(){
        return vX;
    }
    
    public static void setVelX(float vX){
        Camera.vX = vX;
    }
    
    public static float getVelY(){
        return vY;
    }
    
    public static void setVelY(float vY){
        Camera.vY = vY;
    }
    
    public static float getAccX(){
        return aX;
    }
    
    public static void setAccX(float aX){
        Camera.aX = aX;
    }
    
    public static float getAccY(){
        return aY;
    }
    
    public static void setAccY(float aY){
        Camera.aY = aY;
    }
    
}
