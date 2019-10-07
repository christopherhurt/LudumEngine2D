package ludumEngine2D;

import java.awt.Font;

/**
 * Represents a font that can be used to render GUI labels. These fonts need to be synchronized with the dimension of
 * the window, as the size of Java Font objects is measured in pixels. These objects should only be constructable from
 * the GUIFontFactory class.
 *
 * @author Chris Hurt
 * @version 10.04.19
 */
public final class GUIFont {

    private String mName;
    private int mStyle;
    private double mSize;
    private Font mJavaFont;

    /**
     * Package-private constructor.
     *
     * @param pName the face or family name of the font
     * @param pStyle the font styling, e.g. Font.BOLD
     * @param pSize the font size relative to the height of the window
     */
    GUIFont(String pName, int pStyle, double pSize) {
        mName = pName;
        mStyle = pStyle;
        mSize = pSize;
    }

    /**
     * Updates the underlying Java Font. This is called when the dimension of the window changes.
     *
     * @param pWindowHeight the new height of the window in pixels
     */
    void update(double pWindowHeight) {
        mJavaFont = new Font(mName, mStyle, (int)(mSize * pWindowHeight));
    }

    /**
     * @return the Java Font associated with this font
     */
    Font getJavaFont() {
        return mJavaFont;
    }

}
