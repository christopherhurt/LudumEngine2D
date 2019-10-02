package main;

/**
 * Represents a camera that can be moved around the scene to change the player's viewpoint.
 *
 * @author Chris Hurt
 * @version 10.01.19
 */
public interface ICamera {

    /**
     * @return the x position
     */
    float getX();

    /**
     * @return the y position
     */
    float getY();

    /**
     * Sets the x position.
     *
     * @param pX the new x position
     */
    void setX(float pX);

    /**
     * Sets the y position.
     *
     * @param pY the new y position
     */
    void setY(float pY);

}
