package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class ColoredGameObject extends GameObject {
    
    private Color color;
    
    public ColoredGameObject(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id, Color color){
        super(x, y, vX, vY, aX, aY, w, h, id);
        this.color = color;
    }
    
    @Override
    protected void render(Graphics2D g){
        g.setColor(color);
        g.fillRect((int)(getRenderX() * Window.getWidth()), (int)(getRenderY() * Window.getHeight()), (int)(getWidth() * Window.getWidth()), (int)(getHeight() * Window.getHeight()));
        drawBounds(g);
    }
    
    public Color getColor(){
        return color;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
}
