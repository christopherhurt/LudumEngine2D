package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Represents a GUI label component, which can be used to render text to the screen. The appearance of the text can be
 * customized using an assigned GUI font and a color.
 *
 * @author Chris Hurt
 * @version 10.05.19
 */
public final class GUILabel extends AGUIComponent {

    private GUIFont mFont;
    private Color mColor;
    private String mText;

    /**
     * Constructor.
     *
     * @param pFont the font to use for this label
     * @param pColor the color of this label
     * @param pText the text to render for this label
     */
    public GUILabel(GUIFont pFont, Color pColor, String pText) {
        mFont = pFont;
        mColor = pColor;
        mText = pText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void render(Graphics2D pGraphics, Transform pTransform) {
        // Note - the scale component of the transform is entirely ignored
        // X and y positions indicated by the given transform represent the bottom-left corner of the label
        pGraphics.setFont(mFont.getJavaFont());
        pGraphics.setColor(mColor);

        // Get the pixel bounds of the label
        Rectangle2D bounds = pGraphics.getFont().createGlyphVector(pGraphics.getFontRenderContext(), mText)
                .getPixelBounds(null, 0, 0);

        // Draw label where the transform specifies the center point
        pGraphics.drawString(mText, (int)(Window.normalizedToScreen(pTransform.getX()) - bounds.getWidth() / 2d),
                (int)(Window.normalizedToScreen(pTransform.getY()) + bounds.getHeight() / 2));
    }

    /**
     * @return the GUI font
     */
    public GUIFont getFont() {
        return mFont;
    }

    /**
     * Sets the GUI font.
     *
     * @param pFont the GUI font to be set to
     */
    public void setFont(GUIFont pFont) {
        mFont = pFont;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return mColor;
    }

    /**
     * Sets the color.
     *
     * @param pColor the color to be set to
     */
    public void setColor(Color pColor) {
        mColor = pColor;
    }

    /**
     * @return the text String
     */
    public String getText() {
        return mText;
    }

    /**
     * Sets the text String.
     *
     * @param pText the text String to be set to
     */
    public void setText(String pText) {
        mText = pText;
    }

}
