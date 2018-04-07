package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class MenuLabel {
    
    private String text;
    private Font font;
    private String fontType;
    private int fontStyle;
    private float fontSize;
    private Color color;
    private float x, y;
    private String id;
    
    public MenuLabel(String text, String fontType, int fontStyle, float fontSize, Color color, float x, float y, String id){
        this.text = text;
        this.color = color;
        this.x = x;
        this.y = y;
        this.id = id;
        this.fontType = fontType;
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
        font = new Font(fontType, fontStyle, (int)(fontSize * Window.getWidth()));
    }
    
    final void updateFont(){
        font = new Font(fontType, fontStyle, (int)(fontSize * Window.getWidth()));
    }
    
    protected void render(Graphics2D g){
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, (int)(x * Window.getWidth()), (int)(y * Window.getHeight()));
    }
    
    final void render(Graphics2D g, Color color){
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, (int)(x * Window.getWidth()), (int)(y * Window.getHeight()));
    }
    
    final Color getColor(){
        return color;
    }
    
    public String getID(){
        return id;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
}
