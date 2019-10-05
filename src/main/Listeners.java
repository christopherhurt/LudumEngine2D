package main;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Package-private container class for static input listeners. These listeners are attached to the main Canvas when the
 * application is launched. Note that these listeners are executed on the main execution thread, rather than the game
 * thread.
 *
 * @author Chris Hurt
 * @version 10.04.19
 */
final class Listeners {

    /** The set of keys currently down, used to only fired pressed events when the key is first pressed */
    private static final Set<Integer> KEYS_DOWN = new HashSet<>();

    /** The set of mouse buttons currently down, used to only fired pressed events when the button is first pressed */
    private static final Set<Integer> MOUSE_BUTTONS_DOWN = new HashSet<>();

    /** The key listener that creates key events */
    static final KeyListener KEY_LISTENER = new KeyAdapter() {

        /**
         * {@inheritDoc}
         */
        @Override
        public void keyPressed(KeyEvent pEvt) {
            if (!KEYS_DOWN.contains(pEvt.getKeyCode())) {
                Scene.enqueueInputEvent(new main.KeyEvent(EventType.KEY_PRESSED, Game.getCurrentScene(),
                        Window.getScreenMouseLocation(), Window.getWorldMouseLocation(), pEvt.getKeyCode()));
            }
            KEYS_DOWN.add(pEvt.getKeyCode());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void keyReleased(KeyEvent pEvt) {
            Scene.enqueueInputEvent(new main.KeyEvent(EventType.KEY_RELEASED, Game.getCurrentScene(),
                    Window.getScreenMouseLocation(), Window.getWorldMouseLocation(), pEvt.getKeyCode()));
            KEYS_DOWN.remove(pEvt.getKeyCode());
        }

    };

    /** The mouse listener that creates mouse events */
    static final MouseListener MOUSE_LISTENER = new MouseAdapter() {

        /**
         * {@inheritDoc}
         */
        @Override
        public void mousePressed(MouseEvent pEvt) {
            if (!MOUSE_BUTTONS_DOWN.contains(pEvt.getButton())) {
                Scene.enqueueInputEvent(new main.MouseEvent(EventType.MOUSE_BUTTON_PRESSED, Game.getCurrentScene(),
                        Window.getScreenMouseLocation(), Window.getWorldMouseLocation(), pEvt.getButton()));
            }
            MOUSE_BUTTONS_DOWN.add(pEvt.getButton());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseReleased(MouseEvent pEvt) {
            Scene.enqueueInputEvent(new main.MouseEvent(EventType.MOUSE_BUTTON_RELEASED, Game.getCurrentScene(),
                    Window.getScreenMouseLocation(), Window.getWorldMouseLocation(), pEvt.getButton()));
            MOUSE_BUTTONS_DOWN.remove(pEvt.getButton());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseMoved(MouseEvent pEvt) {
            Scene.enqueueInputEvent(new main.MouseEvent(EventType.MOUSE_MOVED, Game.getCurrentScene(),
                    Window.getScreenMouseLocation(), Window.getWorldMouseLocation(), main.MouseEvent.BUTTON_UNKNOWN));
        }

    };

    private Listeners() {
        // Static class, do not initialize
    }

}
