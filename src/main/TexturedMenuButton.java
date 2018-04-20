package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TexturedMenuButton extends MenuButton {
    
    private BufferedImage unhoveredTex;
    private BufferedImage hoveredTex;
    private BufferedImage clickedTex;
    
    public TexturedMenuButton(float x, float y, float w, float h, Texture unhoveredTex, Texture hoveredTex, Texture clickedTex, String id) {
        super(null, x, y, w, h, id);
        
        this.unhoveredTex = unhoveredTex.getImage();
        this.hoveredTex = hoveredTex.getImage();
        this.clickedTex = clickedTex.getImage();
    }
    
    @Override
    protected void render(Graphics2D g) {
        BufferedImage currentTex;
        
        if(isHeld()){
            currentTex = clickedTex;
        }else if(isHovered()){
            currentTex = hoveredTex;
        }else{
            currentTex = unhoveredTex;
        }
        
        g.drawImage(currentTex, (int)(getX() * Window.getWidth()), (int)(getY() * Window.getHeight()), (int)(getWidth() * Window.getWidth()), (int)(getHeight() * Window.getHeight()), null);
    }

}
