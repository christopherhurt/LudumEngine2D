package main;

import java.awt.Graphics2D;

/**
 * Represents an animation appearance that can be used for rendering a game object.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class AnimationAppearance extends AAppearance {

    private double mDuration;
    private Texture[] mTextures;
    private double mCurrentIndex;

    /**
     * Constructor.
     *
     * @param pDuration the duration of the animation in seconds
     * @param pTextures the textures to be used for rendering the animation in order of appearance, repeated references
     *                  to the same texture within this array are acceptable
     */
    public AnimationAppearance(double pDuration, Texture... pTextures) {
        this(pDuration, 0.0f, pTextures);
    }

    /**
     * Constructor.
     *
     * @param pDuration the duration of the animation in seconds
     * @param pInitialProgress the initial normalized progress of the animation where a value of 0 represents the
     *                         beginning of the animation and a value of 1 represents the end
     * @param pTextures the textures to be used for rendering the animation in order of appearance, repeated references
     *                  to the same texture within this array are acceptable
     */
    public AnimationAppearance(double pDuration, double pInitialProgress, Texture... pTextures) {
        mDuration = pDuration;
        mTextures = pTextures;

        setProgress(pInitialProgress);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void render(Graphics2D pGraphics, Transform pTransform) {
        // Update the current texture
        mCurrentIndex = (mCurrentIndex + mTextures.length / mDuration * Time.getDelta()) % mTextures.length;

        Texture currentTexture = mTextures[(int)mCurrentIndex];
        int width = Window.normalizedToScreen(pTransform.getScaleX());
        int height = Window.normalizedToScreen(pTransform.getScaleY());
        pGraphics.drawImage(currentTexture.getImage(), Window.normalizedToScreen(pTransform.getX()) - width / 2,
                Window.normalizedToScreen(pTransform.getY()) - height / 2, width, height, null);
    }

    /**
     * @return the duration of the animation in seconds
     */
    public double getDuration() {
        return mDuration;
    }

    /**
     * Sets the duration of the animation.
     *
     * @param pDuration the duration to be set to in seconds
     */
    public void setDuration(double pDuration) {
        mDuration = pDuration;
    }

    /**
     * @return the current normalized progress of the animation, where a value of 0 represents the beginning of the
     *         animation and a value of 1 represents the end
     */
    public double getProgress() {
        return mCurrentIndex / mTextures.length;
    }

    /**
     * Sets the progress of the animation.
     *
     * @param pProgress the normalized progress of the animation where a value of 0 represents the beginning of the
     *                  animation and a value of 1 represents the end
     */
    public void setProgress(double pProgress) {
        double clampedProgress = Math.max(Math.min(pProgress, 1d), 0d);
        mCurrentIndex = clampedProgress * mTextures.length;
    }

    /**
     * Restarts the animation from the beginning.
     */
    public void restart() {
        setProgress(0d);
    }

}
