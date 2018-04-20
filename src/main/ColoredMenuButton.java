package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class ColoredMenuButton extends MenuButton {
    
    private Color bodyColor;
    private Color hoverColor;
    private Color clickedColor;
    private float borderThickness;
    private float cornerRounding;
    
    public ColoredMenuButton(MenuLabel label, float x, float y, float w, float h, Color bodyColor, Color hoverColor, Color clickedColor, float borderThickness, float cornerRounding, String id) {
        super(label, x, y, w, h, id);
        
        this.bodyColor = bodyColor;
        this.hoverColor = hoverColor;
        this.clickedColor = clickedColor;
        this.borderThickness = borderThickness;
        this.cornerRounding = cornerRounding;
    }
    
    @Override
    protected void render(Graphics2D g){
        Color currentColor;
        
        if(isHeld()){
            currentColor = clickedColor;
        }else if(isHovered()){
            currentColor = hoverColor;
        }else{
            currentColor = getLabel().getColor();
        }
        
        g.setColor(bodyColor);
        g.fillRoundRect((int)(getX() * Window.getWidth()), (int)(getY() * Window.getHeight()), (int)(getWidth() * Window.getWidth()), (int)(getHeight() * Window.getHeight()), (int)(cornerRounding * Window.getWidth()), (int)(cornerRounding * Window.getWidth()));
        g.setColor(currentColor);
        g.setStroke(new BasicStroke(borderThickness * Window.getWidth()));
        g.drawRoundRect((int)(getX() * Window.getWidth()), (int)(getY() * Window.getHeight()), (int)(getWidth() * Window.getWidth()), (int)(getHeight() * Window.getHeight()), (int)(cornerRounding * Window.getWidth()), (int)(cornerRounding * Window.getWidth()));
        getLabel().render(g, currentColor);
    }
    
}
