package main;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Factory class for creating new GUI fonts. These can only be constructed in this factory class so they can be
 * registered for updates that occur internally, i.e. when the window dimension is changed, as Java Font objects
 * have sizes in pixel units, while GUIFont objects have normalized sizes relative to the window height.
 *
 * @author Chris Hurt
 * @version 10.04.19
 */
public final class GUIFontFactory {

    /**
     * The collection of registered GUI fonts. This is made thread-safe because these fonts are created on the game
     * thread and updated on the main thread.
     */
    private static final Collection<GUIFont> FONTS = new CopyOnWriteArrayList<>();

    /**
     * Creates and registers a new GUIFont object.
     *
     * @param pName the face or family name of the font
     * @param pStyle the font styling, e.g. Font.BOLD
     * @param pSize the font size relative to the height of the window
     * @return the new GUIFont object
     */
    public static GUIFont createGUIFont(String pName, int pStyle, double pSize) {
        GUIFont font = new GUIFont(pName, pStyle, pSize);
        FONTS.add(font);
        return font;
    }

    /**
     * Updates the registered fonts with the new window height. This should be called whenever the dimension of the
     * window changes.
     *
     * @param pWindowHeight the new height of the window in pixels
     */
    static void updateFonts(double pWindowHeight) {
        FONTS.forEach(font -> font.update(pWindowHeight));
    }

    private GUIFontFactory() {
        // Static class, do not initialize
    }

}
