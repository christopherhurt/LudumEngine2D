package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Represents a texture that can be applied to a render-able game object or menu component.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class Texture {

    private BufferedImage mImage;

    /**
     * Package-private constructor.
     *
     * @param pImage the buffered image to use as the texture
     */
    Texture(BufferedImage pImage) {
        mImage = pImage;
    }

    /**
     * Constructor.
     *
     * @param pPath the relative file path of the image to use as the texture
     */
    public Texture(String pPath) {
        try {
            mImage = ImageIO.read(getClass().getResource("/" + pPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the underlying buffered image
     */
    BufferedImage getImage() {
        return mImage;
    }

}
