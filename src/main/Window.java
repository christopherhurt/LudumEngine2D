package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window {
    
    private static JFrame frame = null;
    private static Canvas canvas = null;
    private static int width = 0, height = 0;
    private static boolean initialized = false;
    
    private Window(){}
    
    protected static void init(int width, int height, String title, String imageFile, Canvas canvas){
        if(initialized){
            throw new IllegalStateException("Window has already been initialized");
        }
        initialized = true;
        
        Window.canvas = canvas;
        
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(title);
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                SoundBank.deleteAll();
            }
        });
        
        if(imageFile != null){
            imageFile = "/" + imageFile;
            try{
                frame.setIconImage(ImageIO.read(Class.class.getResourceAsStream(imageFile)));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
        frame.add(canvas);
        setSize(width, height);
        frame.setVisible(true);
    }
    
    protected static int getWidth(){
        return width;
    }
    
    protected static int getHeight(){
        return height;
    }
    
    protected static void setSize(int width, int height){
        Window.width = width;
        Window.height = height;
        Game.updateFonts();
        updateSize();
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    
    private static void updateSize(){
        canvas.setSize(new Dimension(width, height));
    }
    
}
