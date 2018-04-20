package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject {
    
    private float x, y;
    private float vX, vY;
    private float aX, aY;
    private float w, h;
    private String id;
    private float boundsSize;
    private int renderedBounds;
    private boolean cameraAffected;
    private boolean updateWhilePaused;
    
    protected GameObject(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id){
        if(w < 0 | h < 0){
            throw new IllegalArgumentException("Width and height must not be negative");
        }
        this.x = x;
        this.y = y;
        this.vX = vX;
        this.vY = vY;
        this.aX = aX;
        this.aY = aY;
        this.w = w;
        this.h = h;
        this.id = id;
        boundsSize = 1;
        renderedBounds = Physics.NO_BOUNDS;
        cameraAffected = true;
        updateWhilePaused = false;
    }
    
    protected abstract void render(Graphics2D g);
    
    final void drawBounds(Graphics2D g){
        g.setColor(Physics.getBoundsColor());
        
        if(renderedBounds == Physics.SINGLE_BOX_BOUNDS){
            g.draw(getBounds());
        }else if(renderedBounds == Physics.FOUR_BOX_BOUNDS){
            g.draw(getUpperBounds());
            g.draw(getLowerBounds());
            g.draw(getLeftBounds());
            g.draw(getRightBounds());
        }else if(renderedBounds != Physics.NO_BOUNDS){
            throw new IllegalStateException("Illegal bounds type being rendered");
        }
    }
    
    final void updatePhysicsComponents(){
        vX += aX;
        vY += aY;
        x += vX;
        y += vY;
    }
    
    protected void update(){
        // Empty
    }
    
    final Rectangle getBounds(){
        int xPos = (int)((x + (1 - boundsSize) * w / 2) * Window.getWidth());
        int yPos = (int)((y + (1 - boundsSize) * h / 2) * Window.getHeight()); 
        int width = (int)(w * Window.getWidth() * boundsSize);
        int height = (int)(h * Window.getHeight() * boundsSize);
        return new Rectangle(xPos, yPos, width, height);
    }
    
    final Rectangle getUpperBounds(){
        int xPos = (int)((x + w * Physics.SIDE_BOUNDS_WIDTH) * Window.getWidth());
        int yPos = (int)(y * Window.getHeight());
        int width = (int)((w - 2 * Physics.SIDE_BOUNDS_WIDTH * w) * Window.getWidth());
        int height = (int)(h / 2 * Window.getHeight());
        
        xPos = (int)(xPos + (1 - boundsSize) / 2 * width);
        yPos = (int)(yPos + (1 - boundsSize) * height);
        width = (int)(width * boundsSize);
        height = (int)(height * boundsSize);
        
        return new Rectangle(xPos, yPos, width, height);
    }
    
    final Rectangle getLowerBounds(){
        int xPos = (int)((x + w * Physics.SIDE_BOUNDS_WIDTH) * Window.getWidth());
        int yPos = (int)((y + h / 2) * Window.getHeight());
        int width = (int)((w - 2 * Physics.SIDE_BOUNDS_WIDTH * w) * Window.getWidth());
        int height = (int)(h / 2 * Window.getHeight());
        
        xPos = (int)(xPos + (1 - boundsSize) / 2 * width);
        width = (int)(width * boundsSize);
        height = (int)(height * boundsSize);
        
        return new Rectangle(xPos, yPos, width, height);
    }
    
    final Rectangle getLeftBounds(){
        int xPos = (int)(x * Window.getWidth());
        int yPos = (int)((y + (1 - Physics.SIDE_BOUNDS_HEIGHT) / 2 * h) * Window.getHeight());
        int width = (int)(Physics.SIDE_BOUNDS_WIDTH * w * Window.getWidth());
        int height = (int)(Physics.SIDE_BOUNDS_HEIGHT * h * Window.getHeight());
        
        xPos = (int)(xPos + (1 - boundsSize) * 2 * width);
        yPos = (int)(yPos + (1 - boundsSize) / 2 * height);
        width = (int)(width * boundsSize);
        height = (int)(height * boundsSize);
        
        return new Rectangle(xPos, yPos, width, height);
    }
    
    final Rectangle getRightBounds(){
        int xPos = (int)((x + (1 - Physics.SIDE_BOUNDS_WIDTH) * w) * Window.getWidth());
        int yPos = (int)((y + (1 - Physics.SIDE_BOUNDS_HEIGHT) / 2 * h) * Window.getHeight());
        int width = (int)(Physics.SIDE_BOUNDS_WIDTH * w * Window.getWidth());
        int height = (int)(Physics.SIDE_BOUNDS_HEIGHT * h * Window.getHeight());
        
        xPos = (int)(xPos - (1 - boundsSize) * width);
        yPos = (int)(yPos + (1 - boundsSize) / 2 * height);
        width = (int)(width * boundsSize);
        height = (int)(height * boundsSize);
        
        return new Rectangle(xPos, yPos, width, height);
    }
    
    public void setBoundsSize(float boundsSize){
        if(boundsSize < 0){
            throw new IllegalArgumentException("Edge buffer less than 0");
        }
        this.boundsSize = boundsSize;
    }
    
    public void setBoundsRender(int type){
        if(type != Physics.NO_BOUNDS && type != Physics.SINGLE_BOX_BOUNDS && type != Physics.FOUR_BOX_BOUNDS){
            throw new IllegalArgumentException("Illegal rendered bounds type");
        }
        renderedBounds = type;
    }
    
    public boolean getCameraAffected(){
        return cameraAffected;
    }
    
    public void setCameraAffected(boolean cameraAffected){
        this.cameraAffected = cameraAffected;
    }
    
    public boolean getUpdateWhilePaused(){
        return updateWhilePaused;
    }
    
    public void setUpdateWhilePaused(boolean update){
        updateWhilePaused = update;
    }
    
    public float getX(){
        return x + (cameraAffected ? 0 : Camera.getX());
    }
    
    final float getRenderX(){
        return x;
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public float getY(){
        return y + (cameraAffected ? 0 : Camera.getY());
    }
    
    final float getRenderY(){
        return y;
    }
    
    public void setY(float y){
        this.y = y;
    }
    
    public float getVelX(){
        return vX;
    }
    
    public void setVelX(float vX){
        this.vX = vX;
    }
    
    public float getVelY(){
        return vY;
    }
    
    public void setVelY(float vY){
        this.vY = vY;
    }
    
    public float getAccX(){
        return aX;
    }
    
    public void setAccX(float aX){
        this.aX = aX;
    }
    
    public float getAccY(){
        return aY;
    }
    
    public void setAccY(float aY){
        this.aY = aY;
    }
    
    public float getWidth(){
        return w;
    }
    
    public void setWidth(float w){
        this.w = w;
    }
    
    public float getHeight(){
        return h;
    }
    
    public void setHeight(float h){
        this.h = h;
    }
    
    public String getID(){
        return id;
    }
    
    public void setID(String id){
        this.id = id;
    }
    
}
