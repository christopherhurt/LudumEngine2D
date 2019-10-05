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
    private GameObject mParent = null;
    private IHandler mHandler = null;
    private Transform mTransform = null;
    private AAppearance mAppearance = null;
    private Kinematics mKinematics = null;
    private AGUIComponent mGUIComponent = null;
    private BoundingBox mBoundingBox = null;

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
     * @param pParent the parent of this game object
     * @return this builder
     */
    public GameObjectBldr withParent(GameObject pParent) {
        mParent = pParent;
        return this;
    }

    /**
     * @param pHandler the event handler to use
     * @return this builder
     */
    public GameObjectBldr withHandler(IHandler pHandler) {
        mHandler = pHandler;
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
     * @param pGUIComponent the GUI component to use
     * @return this builder
     */
    public GameObjectBldr withGUIComponent(AGUIComponent pGUIComponent) {
        mGUIComponent = pGUIComponent;
        return this;
    }

    /**
     * @param pBoundingBox the bounding box component to use
     * @return this builder
     */
    public GameObjectBldr withBoundingBox(BoundingBox pBoundingBox) {
        mBoundingBox = pBoundingBox;
        return this;
    }

    /**
     * @return the built game object
     */
    public GameObject build() {
        int id = sIdCounter++;

        // Can't have any of these components without a transform
        if ((mAppearance != null || mKinematics != null || mGUIComponent != null || mBoundingBox != null)
                && mTransform == null) {
            Debug.warn("Using default transform for GameObject with id " + id);
            mTransform = new Transform();
        }

        return new GameObject(id, mZIndex, mTag, mParent, mHandler, mTransform, mAppearance, mKinematics,
                mGUIComponent, mBoundingBox);
    }

}
