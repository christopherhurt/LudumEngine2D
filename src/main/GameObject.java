package main;

import java.util.Optional;

/**
 * Represents a game object, the basic unit of the game object. Components can be added to each game object individually
 * to give each one properties like locational data, appearance, event handlers, etc.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class GameObject {

    private int mId;
    private int mZIndex;
    private String mTag;
    private Transform mTransform;
    private AAppearance mAppearance;
    private Kinematics mKinematics;

    /**
     * Package-private constructor.
     *
     * @param pId the game object's unique id
     * @param pZIndex the z-index used to determine update and render order
     * @param pTag the tag identifier, not necessarily unique
     * @param pTransform the transform containing the game object's position, rotation, and scale
     * @param pAppearance the appearance of the game object, how it's rendered
     * @param pKinematics the kinematics of the game object describing its motion
     */
    GameObject(int pId, int pZIndex, String pTag, Transform pTransform, AAppearance pAppearance, Kinematics pKinematics) {
        mId = pId;
        mZIndex = pZIndex;
        mTag = pTag;
        mTransform = pTransform;
        mAppearance = pAppearance;
        mKinematics = pKinematics;
    }

    /**
     * @return the unique id
     */
    public int getId() {
        return mId;
    }

    /**
     * @return the z-index
     */
    public int getZIndex() {
        return mZIndex;
    }

    /**
     * @return the tag
     */
    public Optional<String> getTag() {
        return Optional.ofNullable(mTag);
    }

    /**
     * @return the transform component
     */
    public Optional<Transform> getTransform() {
        return Optional.ofNullable(mTransform);
    }

    /**
     * @return the appearance component
     */
    public Optional<AAppearance> getAppearance() {
        return Optional.ofNullable(mAppearance);
    }

    /**
     * @return the kinematics component
     */
    public Optional<Kinematics> getKinematics() {
        return Optional.ofNullable(mKinematics);
    }

    /**
     * Sets the z-index.
     *
     * @param pZIndex the z-index to be set to
     */
    public void setZIndex(int pZIndex) {
        mZIndex = pZIndex;
    }

    /**
     * Sets the tag.
     *
     * @param pTag the tag to be set to
     */
    public void setTag(String pTag) {
        mTag = pTag;
    }

    /**
     * Sets the transform component.
     *
     * @param pTransform the transform to be set to
     */
    public void setTransform(Transform pTransform) {
        mTransform = pTransform;
    }

    /**
     * Sets the appearance component.
     *
     * @param pAppearance the appearance to be set to
     */
    public void setAppearance(AAppearance pAppearance) {
        mAppearance = pAppearance;
    }

    /**
     * Sets the kinematics component.
     *
     * @param pKinematics the kinematics to be set to
     */
    public void setKinematics(Kinematics pKinematics) {
        mKinematics = pKinematics;
    }

}
