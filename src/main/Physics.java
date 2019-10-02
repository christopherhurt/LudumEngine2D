package main;

import java.awt.Color;
import java.awt.Rectangle;

public class Physics {
    
    public static final int NO_BOUNDS = 0;
    public static final int SINGLE_BOX_BOUNDS = 1;
    public static final int FOUR_BOX_BOUNDS = 2;
    
    public static final int WHOLE_BOUNDS = 3;
    public static final int LEFT_BOUNDS = 4;
    public static final int RIGHT_BOUNDS = 5;
    public static final int UPPER_BOUNDS = 6;
    public static final int LOWER_BOUNDS = 7;
    
    protected static final float SIDE_BOUNDS_WIDTH = 0.25f;
    protected static final float SIDE_BOUNDS_HEIGHT = 0.75f;
    
    private static float gravity = 0;
    private static Color boundsColor = new Color(255, 0, 0);
    
    private Physics(){}
    
    public static boolean checkCollision(GameObject obj1, GameObject obj2, int obj1Bounds, int obj2Bounds){
        Rectangle obj1Rect = getRectangle(obj1, obj1Bounds);
        Rectangle obj2Rect = getRectangle(obj2, obj2Bounds);
        
        adjustBoundsForCamera(obj1, obj1Rect);
        adjustBoundsForCamera(obj2, obj2Rect);
        
        return obj1Rect.intersects(obj2Rect);
    }
    
    private static Rectangle getRectangle(GameObject obj, int objBounds){
        switch(objBounds){
            case WHOLE_BOUNDS:
                return obj.getBounds();
            case LEFT_BOUNDS:
                return obj.getLeftBounds();
            case RIGHT_BOUNDS:
                return obj.getRightBounds();
            case UPPER_BOUNDS:
                return obj.getUpperBounds();
            case LOWER_BOUNDS:
                return obj.getLowerBounds();
            default:
                throw new IllegalArgumentException("Illegal type of bounding box used for checking collision");
        }
    }
    
    private static void adjustBoundsForCamera(GameObject obj, Rectangle bounds){
        if(!obj.getCameraAffected()){
            bounds.setBounds((int)(bounds.getX() + FreeCamera.getX() * Window.getWidth()), (int)(bounds.getY() + FreeCamera.getY() * Window.getHeight()),
                (int)bounds.getWidth(), (int)bounds.getHeight());
        }
    }
    
    public static float getGravity(){
        return gravity;
    }
    
    public static void setGravity(float gravity){
        Physics.gravity = gravity;
    }
    
    public static Color getBoundsColor(){
        return boundsColor;
    }
    
    public static void setBoundsColor(Color color){
        boundsColor = color;
    }
    
}
