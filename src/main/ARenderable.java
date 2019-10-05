package main;

import java.awt.Graphics2D;

/**
 * Package-private class that represents a renderable game object component. For organizational purposes, this class
 * is non-accessible to the user of the engine, but the interface for how rendering is handled can be modified here to
 * affect all components that inherit it. This is an abstract class rather than an interface to keep the render()
 * method package-private and non-accessible to the engine user.
 *
 * @author Chris Hurt
 * @version 10.04.19
 */
abstract class ARenderable {

    /**
     * Renders this component.
     *
     * @param pGraphics the graphics context to use for rendering
     * @param pTransform the transform to use for rendering
     */
    abstract void render(Graphics2D pGraphics, Transform pTransform);

}
