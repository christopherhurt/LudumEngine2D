package main;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represents a solid color appearance of a game object.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class ColorAppearance extends AAppearance {

    private Color mColor;

    /**
     * Default constructor.
     */
    public ColorAppearance() {
        this(Color.BLUE);
    }

    /**
     * Constructor.
     *
     * @param pColor the color of the appearance
     */
    public ColorAppearance(Color pColor) {
        mColor = pColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void updateAndRender(Graphics2D pGraphics, Transform pTransform) {
        pGraphics.setColor(mColor);
        pGraphics.fillRect(Window.normalizedToScreen(pTransform.getX()), Window.normalizedToScreen(pTransform.getY()),
                Window.normalizedToScreen(pTransform.getWidth()), Window.normalizedToScreen(pTransform.getHeight()));
    }

}
