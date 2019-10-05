package main;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A spritesheet implementation that can hold multiple textures which can be extracted individually.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class SpriteSheet {

    private static Color sBackgroundColor = new Color(255, 0, 255);
    private static Color sLineColor = new Color(64, 64, 64);

    private int mRows;
    private int mColumns;
    private BufferedImage mImage;

    /**
     * Constructor.
     *
     * @param pPath the relative file path of the spritesheet
     * @param pRows the number of rows in the spritesheet
     * @param pColumns the number of columns in the spritesheet
     */
    public SpriteSheet(String pPath, int pRows, int pColumns) {
        mRows = pRows;
        mColumns = pColumns;

        try {
            mImage = ImageIO.read(getClass().getResource("/" + pPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mImage.getWidth() % mColumns != 0) {
            throw new IllegalArgumentException("Spritesheet " + pPath + " does not have equally-sized columns");
        } else if (mImage.getHeight() % mRows != 0) {
            throw new IllegalArgumentException("Spritesheet " + pPath + " does not have equally-sized rows");
        }
    }

    /**
     * Creates a texture from the specified region on the spritesheet.
     *
     * @param pRow the row index of the texture image on the spritesheet, starting at 0
     * @param pColumn the column index of the texture image on the spritesheet, starting at 0
     * @return the texture constructed from the specified region on the spritesheet
     */
    public Texture getTexture(int pRow, int pColumn) {
        // Get specified region of the spritesheet
        int width = mImage.getWidth() / mColumns;
        int height = mImage.getHeight() / mRows;
        BufferedImage rawImage = mImage.getSubimage(pColumn * width, pRow * height, width, height);

        // Extract raw image data to ARGB format, ignoring spritesheet background and line colors
        BufferedImage texImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int backgroundRGB = sBackgroundColor.getRGB();
        int lineRGB = sLineColor.getRGB();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixelRGB = rawImage.getRGB(x, y);

                if (pixelRGB == backgroundRGB || pixelRGB == lineRGB) {
                    texImage.setRGB(x, y, 0x0);
                } else {
                    texImage.setRGB(x, y, pixelRGB);
                }
            }
        }

        return new Texture(texImage);
    }

    /**
     * Sets the background color of all spritesheets, which is to be ignored when extracting textures.
     *
     * @param pColor the background color
     */
    public static void setBackgroundColor(Color pColor) {
        sBackgroundColor = pColor;
    }

    /**
     * Sets the grid line color of all spritesheets, which is to be ignored when extracting textures.
     *
     * @param pColor the grid line color
     */
    public static void setLineColor(Color pColor) {
        sLineColor = pColor;
    }

}
