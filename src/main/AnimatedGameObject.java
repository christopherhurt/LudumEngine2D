package main;

import java.awt.Graphics2D;

public class AnimatedGameObject extends GameObject {
    
    private Animation animation;
    
    public AnimatedGameObject(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id, Animation animation){
        super(x, y, vX, vY, aX, aY, w, h, id);
        this.animation = animation;
    }
    
    @Override
    protected void render(Graphics2D g){
        g.drawImage(animation.getCurrentTexture().getImage(), (int)(getRenderX() * Window.getWidth()), (int)(getRenderY() * Window.getHeight()), (int)(getWidth() * Window.getWidth()), (int)(getHeight() * Window.getHeight()), null);
        drawBounds(g);
    }
    
    public Animation getAnimation(){
        return animation;
    }
    
    public void setAnimation(Animation animation){
        this.animation = animation;
    }
    
}
