package main;

import java.awt.*;

/**
 * Abstract class that represents the appearance of a game object. This can be a solid color, texture, animation, etc.
 * This is an abstract class and not an interface to keep the mechanisms of how the appearance is rendered
 * package-private.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public abstract class AAppearance {

    /**
     * Updates and renders the appearance using the given transform.
     *
     * @param pGraphics the graphics context to use for rendering
     * @param pTransform the transform to use for rendering
     */
    abstract void updateAndRender(Graphics2D pGraphics, Transform pTransform);

}
