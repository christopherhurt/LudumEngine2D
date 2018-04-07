package main;

import java.awt.Graphics2D;

public class TexturedGameObject extends GameObject {
    
    private Texture texture;
    
    public TexturedGameObject(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id, Texture texture){
        super(x, y, vX, vY, aX, aY, w, h, id);
        this.texture = texture;
    }
    
    @Override
    protected void render(Graphics2D g){
        g.drawImage(texture.getImage(), (int)(getRenderX() * Window.getWidth()), (int)(getRenderY() * Window.getHeight()), (int)(getWidth() * Window.getWidth()), (int)(getHeight() * Window.getHeight()), null);
        drawBounds(g);
    }
    
    public Texture getTexture(){
        return texture;
    }
    
    public void setTexture(Texture texture){
        this.texture = texture;
    }
    
}
