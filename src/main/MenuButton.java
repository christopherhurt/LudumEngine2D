//package main;
//
//import java.awt.Graphics2D;
//import java.awt.Point;
//import java.awt.event.MouseEvent;
//
//@Deprecated
//public abstract class MenuButton {
//
//    private MenuLabel label;
//    private float x, y, w, h;
//    private String id;
//
//    private boolean wasPressed;
//    private boolean isClicked;
//    private boolean isHovered;
//    private boolean isHeld;
//    private boolean isReleased;
//
//    public MenuButton(MenuLabel label, float x, float y, float w, float h, String id){
//        this.label = label;
//        this.x = x;
//        this.y = y;
//        this.w = w;
//        this.h = h;
//        this.id = id;
//
//        wasPressed = false;
//        isClicked = false;
//        isHovered = false;
//        isHeld = false;
//        isReleased = false;
//    }
//
//    final void checkButtons(){
//        boolean mouseOver = isMouseOver((int)(x * WindowOLD.getWidth()), (int)(y * WindowOLD.getHeight()), (int)(w * WindowOLD.getWidth()), (int)(h * WindowOLD.getHeight()));
//
//        isClicked = mouseOver && Input.isButtonPressed(MouseEvent.BUTTON1);
//        if(isClicked){
//            wasPressed = true;
//        }
//
//        isReleased = wasPressed && Input.isButtonReleased(MouseEvent.BUTTON1);
//        if(isReleased){
//            wasPressed = false;
//        }
//
//        isHovered = mouseOver;
//        isHeld = wasPressed;
//    }
//
//    protected void update(){
//        // Empty
//    }
//
//    private static boolean isMouseOver(int xPos, int yPos, int width, int height){
//        Point mousePos = GameOLD.getMouseLocation();
//        if(mousePos != null){
//            int mouseX = (int)mousePos.getX();
//            int mouseY = (int)mousePos.getY();
//
//            boolean xInside = mouseX > xPos && mouseX < xPos + width;
//            boolean yInside = mouseY > yPos && mouseY < yPos + height;
//
//            return xInside && yInside;
//        }else{
//            return false;
//        }
//    }
//
//    protected abstract void render(Graphics2D g);
//
//    public MenuLabel getLabel(){
//        return label;
//    }
//
//    public float getX(){
//        return x;
//    }
//
//    public void setX(float x){
//        this.x = x;
//    }
//
//    public float getY(){
//        return y;
//    }
//
//    public void setY(float y){
//        this.y = y;
//    }
//
//    public float getWidth(){
//        return w;
//    }
//
//    public void setWidth(float w){
//        this.w = w;
//    }
//
//    public float getHeight(){
//        return h;
//    }
//
//    public void setHeight(float h){
//        this.h = h;
//    }
//
//    public String getID(){
//        return id;
//    }
//
//    public boolean isClicked(){
//        return isClicked;
//    }
//
//    public boolean isHovered(){
//        return isHovered;
//    }
//
//    public boolean isHeld(){
//        return isHeld;
//    }
//
//    public boolean isReleased(){
//        return isReleased;
//    }
//
//}
