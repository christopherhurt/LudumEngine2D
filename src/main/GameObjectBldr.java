package main;

/**
 * Builder class for game objects.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public final class GameObjectBldr {

    private static int sIdCounter = 0;

    private int mZIndex = 0;
    private String mTag = null;
    private Transform mTransform = null;
    private AAppearance mAppearance = null;
    private Kinematics mKinematics = null;

    /**
     * @param pZIndex the z-index to use
     * @return this builder
     */
    public GameObjectBldr withZIndex(int pZIndex) {
        mZIndex = pZIndex;
        return this;
    }

    /**
     * @param pTag the tag to use
     * @return this builder
     */
    public GameObjectBldr withTag(String pTag) {
        mTag = pTag;
        return this;
    }

    /**
     * @param pTransform the transform to use
     * @return this builder
     */
    public GameObjectBldr withTransform(Transform pTransform) {
        mTransform = pTransform;
        return this;
    }

    /**
     * @param pAppearance the appearance to use
     * @return this builder
     */
    public GameObjectBldr withAppearance(AAppearance pAppearance) {
        mAppearance = pAppearance;
        return this;
    }

    /**
     * @param pKinematics the kinematics to use
     * @return this builder
     */
    public GameObjectBldr withKinematics(Kinematics pKinematics) {
        mKinematics = pKinematics;
        return this;
    }

    /**
     * @return the built game object
     */
    public GameObject build() {
        int id = sIdCounter++;

        // Can't have an appearance or kinematics component without a transform
        if ((mAppearance != null || mKinematics != null) && mTransform == null) {
            Debug.warn("Using default transform for GameObject with id " + id);
            mTransform = new Transform();
        }

        return new GameObject(id, mZIndex, mTag, mTransform, mAppearance, mKinematics);
    }

}