package main;

import java.awt.Graphics2D;

/**
 * Represents a texture appearance that can be used to render a game object.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class TextureAppearance extends AAppearance {

    private Texture mTexture;

    /**
     * Constructor.
     *
     * @param pTexture the texture to render with
     */
    public TextureAppearance(Texture pTexture) {
        mTexture = pTexture;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void updateAndRender(Graphics2D pGraphics, Transform pTransform) {
        int screenWidth = Window.normalizedToScreen(pTransform.getWidth());
        int screenHeight = Window.normalizedToScreen(pTransform.getHeight());
        pGraphics.rotate(Math.toRadians(pTransform.getRotation()), screenWidth / 2d, screenHeight / 2d);
        pGraphics.drawImage(mTexture.getImage(), Window.normalizedToScreen(pTransform.getX()),
                Window.normalizedToScreen(pTransform.getY()), screenWidth, screenHeight, null);
    }

}
