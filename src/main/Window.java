package main;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Represents the window used to display the game.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class Window {

    private static final Canvas CANVAS = new Canvas();

    private static boolean sInitialized = false;
    private static JFrame sFrame;
    private static int sHeight;

    /**
     * Initializes the game window.
     *
     * @param pWidth the initial width of the window in pixels
     * @param pHeight the initial height of the window in pixels
     * @param pTitle the window title
     * @param pImagePath the relative file path of the window's image icon, can be null
     */
    static void init(int pWidth, int pHeight, String pTitle, String pImagePath) {
        if (sInitialized) {
            throw new IllegalStateException("Window has already been initialized");
        }
        sInitialized = true;

        sFrame = new JFrame(pTitle);
        sFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sFrame.setResizable(false);
        sFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent pEvt) {
                Game.close();
            }
        });

        if (pImagePath != null) {
            try {
                sFrame.setIconImage(ImageIO.read(Class.class.getResourceAsStream(pImagePath)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // These should happen last, in this order
        sFrame.add(CANVAS);
        setDimension(new Dimension(pWidth, pHeight));
        CANVAS.createBufferStrategy(3);
        sFrame.setVisible(true);
        CANVAS.requestFocus();
    }

    /**
     * @return the dimension of the window in pixels
     */
    public static Dimension getDimension() {
        checkInitialization();
        return CANVAS.getSize();
    }

    /**
     * Sets the dimension of the window.
     *
     * @param pDimension the new dimension to be set to in pixels
     */
    public static void setDimension(Dimension pDimension) {
        checkInitialization();
        sHeight = (int)pDimension.getHeight();
        CANVAS.setSize(pDimension);
        sFrame.pack();
    }

    /**
     * @return the width / height aspect ratio of the window
     */
    public static double getAspectRatio() {
        checkInitialization();
        Dimension dimension = getDimension();
        return dimension.getWidth() / dimension.getHeight();
    }

    /**
     * Converts a normalized 0-1 value to a screen space value. All normalized values are relative to the height of the
     * window.
     *
     * @param pValue the value to convert
     * @return the converted value in pixels
     */
    static int normalizedToScreen(double pValue) {
        checkInitialization();
        return (int)(pValue * sHeight);
    }

    /**
     * @return the canvas used to render to this window
     */
    static Canvas getCanvas() {
        checkInitialization();
        return CANVAS;
    }

    /**
     * Destroys the window, causing the game to terminate.
     */
    static void destroy() {
        checkInitialization();
        sFrame.dispose();
    }

    /**
     * Checks that the window has been initialized.
     */
    private static void checkInitialization() {
        if (!sInitialized) {
            throw new IllegalStateException("Window has not been initialized");
        }
    }

    private Window() {
        // Static class, do not initialize
    }

}
