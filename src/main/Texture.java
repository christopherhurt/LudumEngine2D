package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class Texture {
    
    private BufferedImage img;
    
    public Texture(String file){
        file = "/" + file;
        try{
            img = ImageIO.read(getClass().getResource(file));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    protected Texture(BufferedImage img){
        this.img = img;
    }
    
    public BufferedImage getImage(){
        return img;
    }
    
}
