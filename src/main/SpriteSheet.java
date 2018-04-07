package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class SpriteSheet {
    
    private static Color backgroundColor = new Color(255, 0, 255);
    private static Color lineColor = new Color(64, 64, 64);
    
    private BufferedImage spriteSheet;
    private int numColumns, numRows;
    
    public SpriteSheet(String file, int numRows, int numColumns){
        this.numRows = numRows;
        this.numColumns = numColumns;

        file = "/" + file;
        try{
            spriteSheet = ImageIO.read(getClass().getResource(file));
        }catch(IOException e){
            e.printStackTrace();
        }
        
        if(spriteSheet.getWidth() % numColumns != 0){
            throw new IllegalArgumentException("Sprite sheet columns must have equal sizes");
        }
        if(spriteSheet.getHeight() % numRows != 0){
            throw new IllegalArgumentException("Sprite sheet rows must have equal sizes");
        }
    }
    
    public Texture getTexture(int row, int column){
        int spriteSheetWidth = spriteSheet.getWidth();
        int spriteSheetHeight = spriteSheet.getHeight();
        int imgWidth = spriteSheetWidth / numColumns;
        int imgHeight = spriteSheetHeight / numRows;
        BufferedImage loadedImg = spriteSheet.getSubimage(column * imgWidth, row * imgHeight, imgWidth, imgHeight);
        BufferedImage img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
        
        int background = backgroundColor.getRGB();
        int lines = lineColor.getRGB();
        for(int x = 0; x < imgWidth; x++){
            for(int y = 0; y < imgHeight; y++){
                int rgb = loadedImg.getRGB(x, y);
                
                if(rgb == background || rgb == lines){
                    img.setRGB(x, y, 0x00ffffff);
                }else{
                    img.setRGB(x, y, rgb);
                }
            }
        }
        
        return new Texture(img);
    }
    
    public static void setBackgroundColor(Color color){
        backgroundColor = color;
    }
    
    public static void setLineColor(Color color){
        lineColor = color;
    }
    
}
