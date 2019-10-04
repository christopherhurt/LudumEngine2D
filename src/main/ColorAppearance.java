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
        // TODO: address these issues, fix glitchiness
//        pGraphics.getTransform(); // TODO: remove
        pGraphics.setColor(mColor);
        int screenWidth = Window.normalizedToScreen(pTransform.getWidth());
        int screenHeight = Window.normalizedToScreen(pTransform.getHeight());
        tester += 0.0005;
//        pGraphics.setTransform(AffineTransform.getRotateInstance(tester));
//        pGraphics.setTransform(AffineTransform.getRotateInstance(tester, 0.5 * Window.getDimension().getHeight(), 0.5 * Window.getDimension().getHeight()));
//        pGraphics.rotate(tester, 0.05 * Window.getDimension().getHeight(), 0.05 * Window.getDimension().getHeight());
        pGraphics.rotate(Math.toRadians(pTransform.getRotation()), screenWidth / 2d, screenHeight / 2d);
        pGraphics.fillRect(Window.normalizedToScreen(pTransform.getX()), Window.normalizedToScreen(pTransform.getY()),
                screenWidth, screenHeight);
    }

    double tester = 0d;

}
