package main;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Optional;

/**
 * Represents a GUI button component. This button can have a different appearance when it's idle, pressed, or hovered
 * over. This button can also fire GUI_BUTTON_PRESSED and GUI_BUTTON_RELEASED events when it's pressed or released,
 * respectively.
 *
 * @author Chris Hurt
 * @version 10.05.19
 */
public final class GUIButton extends AGUIComponent {

    private Texture mDefaultTexture;
    private Texture mPressedTexture;
    private Texture mHoveredTexture;

    private boolean mPressed = false;
    private boolean mHovered = false;

    /**
     * Constructor.
     *
     * @param pDefaultTexture the default texture to render
     */
    public GUIButton(Texture pDefaultTexture) {
        this(pDefaultTexture, null, null);
    }

    /**
     * Constructor.
     *
     * @param pDefaultTexture the default texture to render
     * @param pPressedTexture the texture to render when the button is pressed
     */
    public GUIButton(Texture pDefaultTexture, Texture pPressedTexture) {
        this(pDefaultTexture, pPressedTexture, null);
    }

    /**
     * Constructor.
     *
     * @param pDefaultTexture the default texture to render
     * @param pPressedTexture the texture to render when the button is pressed
     * @param pHoveredTexture the texture to render when the button is hovered over
     */
    public GUIButton(Texture pDefaultTexture, Texture pPressedTexture, Texture pHoveredTexture) {
        mDefaultTexture = pDefaultTexture;
        mPressedTexture = pPressedTexture;
        mHoveredTexture = pHoveredTexture;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void render(Graphics2D pGraphics, Transform pTransform) {
        Texture currentTexture = null;
        if (mHovered) {
            if (mPressed) {
                currentTexture = mPressedTexture;
            } else {
                currentTexture = mHoveredTexture;
            }
        }
        if (currentTexture == null) {
            currentTexture = mDefaultTexture;
        }

        // Note - the x and y positions indicated by the given transform represent the bottom-left corner of the button
        int height = Window.normalizedToScreen(pTransform.getScaleY());
        pGraphics.drawImage(currentTexture.getImage(), Window.normalizedToScreen(pTransform.getX()),
                Window.normalizedToScreen(pTransform.getY()) - height,
                Window.normalizedToScreen(pTransform.getScaleX()), height, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void update(MouseEvent pEvt, GameObject pGameObject) {
        Optional<Point2D.Double> relativePoint = pGameObject.getRelativeMouseLocation(pEvt);

        if (relativePoint.isPresent()) {
            boolean inBounds = withinBounds(relativePoint.get());

            switch (pEvt.getType()) {
                case MOUSE_MOVED:
                    mHovered = inBounds;
                    mPressed &= mHovered;
                    break;
                case MOUSE_BUTTON_PRESSED:
                    if (inBounds) {
                        mPressed = true;
                        new GUIEvent(EventType.GUI_BUTTON_PRESSED, pEvt.getScene()).fire(List.of(pGameObject));
                    }
                    break;
                case MOUSE_BUTTON_RELEASED:
                    if (inBounds) {
                        mPressed = false;
                        new GUIEvent(EventType.GUI_BUTTON_RELEASED, pEvt.getScene()).fire(List.of(pGameObject));
                    }
                    break;
                default:
                    break;
            }
        } else {
            Debug.error("Failed to calculate relative mouse location for GUIButton relative to GameObject " +
                    "with id " + pGameObject.getId());
        }
    }

    /**
     * @param pPoint the relative point to check, normalized relative to the bounds of some GUI button
     * @return whether the given relative point indicates it's within the bounds of the GUI button
     */
    private static boolean withinBounds(Point2D.Double pPoint) {
        return pPoint.getX() >= 0d && pPoint.getX() <= 1d && pPoint.getY() >= 0d && pPoint.getY() <= 1d;
    }

    /**
     * @return the default texture
     */
    public Texture getDefaultTexture() {
        return mDefaultTexture;
    }

    /**
     * @return the pressed texture
     */
    public Texture getPressedTexture() {
        return mPressedTexture;
    }

    /**
     * @return the hovered texture
     */
    public Texture getHoveredTexture() {
        return mHoveredTexture;
    }

}
