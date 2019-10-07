package ludumEngine2D;

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
    void render(Graphics2D pGraphics, Transform pTransform) {
        pGraphics.setColor(mColor);
        int width = Window.normalizedToScreen(pTransform.getScaleX());
        int height = Window.normalizedToScreen(pTransform.getScaleY());
        pGraphics.fillRect(Window.normalizedToScreen(pTransform.getX()) - width / 2,
                Window.normalizedToScreen(pTransform.getY()) - height / 2,
                width, height);
    }

}
