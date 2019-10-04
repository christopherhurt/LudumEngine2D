//package main;
//
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Graphics2D;
//
//@Deprecated
//public class ColoredMenuButton extends MenuButton {
//
//    private Color bodyColor;
//    private Color hoverColor;
//    private Color clickedColor;
//    private float borderThickness;
//    private float cornerRounding;
//
//    public ColoredMenuButton(MenuLabel label, float x, float y, float w, float h, Color bodyColor, Color hoverColor, Color clickedColor, float borderThickness, float cornerRounding, String id) {
//        super(label, x, y, w, h, id);
//
//        this.bodyColor = bodyColor;
//        this.hoverColor = hoverColor;
//        this.clickedColor = clickedColor;
//        this.borderThickness = borderThickness;
//        this.cornerRounding = cornerRounding;
//    }
//
//    @Override
//    protected void render(Graphics2D g){
//        Color currentColor;
//
//        if(isHeld()){
//            currentColor = clickedColor;
//        }else if(isHovered()){
//            currentColor = hoverColor;
//        }else{
//            currentColor = getLabel().getColor();
//        }
//
//        g.setColor(bodyColor);
//        g.fillRoundRect((int)(getX() * WindowOLD.getWidth()), (int)(getY() * WindowOLD.getHeight()), (int)(getWidth() * WindowOLD.getWidth()), (int)(getHeight() * WindowOLD.getHeight()), (int)(cornerRounding * WindowOLD.getWidth()), (int)(cornerRounding * WindowOLD.getWidth()));
//        g.setColor(currentColor);
//        g.setStroke(new BasicStroke(borderThickness * WindowOLD.getWidth()));
//        g.drawRoundRect((int)(getX() * WindowOLD.getWidth()), (int)(getY() * WindowOLD.getHeight()), (int)(getWidth() * WindowOLD.getWidth()), (int)(getHeight() * WindowOLD.getHeight()), (int)(cornerRounding * WindowOLD.getWidth()), (int)(cornerRounding * WindowOLD.getWidth()));
//
//        MenuLabel label = getLabel();
//
//        if(label != null) {
//            label.render(g, currentColor);
//        }
//    }
//
//}
