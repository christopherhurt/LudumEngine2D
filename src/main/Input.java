//package main;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//@Deprecated
//public class Input implements KeyListener, MouseListener {
//
//    private static final int NUMBER_OF_KEYS = 65490;
//    private static final int NUMBER_OF_BUTTONS = 508;
//
//    private static boolean[] keysPressed = new boolean[NUMBER_OF_KEYS];
//    private static boolean[] keysDown = new boolean[NUMBER_OF_KEYS];
//    private static boolean[] keysReleased = new boolean[NUMBER_OF_KEYS];
//
//    private static boolean[] buttonsPressed = new boolean[NUMBER_OF_BUTTONS];
//    private static boolean[] buttonsDown = new boolean[NUMBER_OF_BUTTONS];
//    private static boolean[] buttonsReleased = new boolean[NUMBER_OF_BUTTONS];
//
//    private static boolean[] keysJustPressed = new boolean[NUMBER_OF_KEYS];
//    private static boolean[] keysJustReleased = new boolean[NUMBER_OF_KEYS];
//
//    private static boolean[] buttonsJustPressed = new boolean[NUMBER_OF_BUTTONS];
//    private static boolean[] buttonsJustReleased = new boolean[NUMBER_OF_BUTTONS];
//
//    Input(){}
//
//    protected static void update(){
//        for(int i = 0; i < NUMBER_OF_KEYS; i++){
//            keysPressed[i] = keysJustPressed[i] && !keysDown[i];
//            keysReleased[i] = keysJustReleased[i];
//
//            if(keysPressed[i]){
//                keysDown[i] = true;
//            }
//
//            if(keysReleased[i]){
//                keysDown[i] = false;
//            }
//
//            keysJustPressed[i] = false;
//            keysJustReleased[i] = false;
//        }
//
//        for(int i = 0; i < NUMBER_OF_BUTTONS; i++){
//            buttonsPressed[i] = buttonsJustPressed[i] && !buttonsDown[i];
//            buttonsReleased[i] = buttonsJustReleased[i];
//
//            if(buttonsPressed[i]){
//                buttonsDown[i] = true;
//            }
//
//            if(buttonsReleased[i]){
//                buttonsDown[i] = false;
//            }
//
//            buttonsJustPressed[i] = false;
//            buttonsJustReleased[i] = false;
//        }
//    }
//
//    public static boolean isKeyPressed(int keyCode){
//        return keysPressed[keyCode];
//    }
//
//    public static boolean isKeyDown(int keyCode){
//        return keysDown[keyCode];
//    }
//
//    public static boolean isKeyReleased(int keyCode){
//        return keysReleased[keyCode];
//    }
//
//    public static boolean isButtonPressed(int button){
//        return buttonsPressed[button];
//    }
//
//    public static boolean isButtonDown(int button){
//        return buttonsDown[button];
//    }
//
//    public static boolean isButtonReleased(int button){
//        return buttonsReleased[button];
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        int key = e.getKeyCode();
//
//        if(key < 0 || key >= NUMBER_OF_KEYS){
//            throw new IllegalStateException("Invalid key pressed");
//        }
//
//        keysJustPressed[key] = true;
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        int key = e.getKeyCode();
//
//        if(key < 0 || key >= NUMBER_OF_KEYS){
//            throw new IllegalStateException("Invalid key released");
//        }
//
//        keysJustReleased[key] = true;
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        int button = e.getButton();
//
//        if(button < 0 || button >= NUMBER_OF_BUTTONS){
//            throw new IllegalStateException("Invalid button pressed");
//        }
//
//        buttonsJustPressed[button] = true;
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        int button = e.getButton();
//
//        if(button < 0 || button >= NUMBER_OF_BUTTONS){
//            throw new IllegalStateException("Invalid button released");
//        }
//
//        buttonsJustReleased[button] = true;
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e){}
//    @Override
//    public void mouseClicked(MouseEvent e){}
//    @Override
//    public void mouseEntered(MouseEvent e){}
//    @Override
//    public void mouseExited(MouseEvent e){}
//
//}
