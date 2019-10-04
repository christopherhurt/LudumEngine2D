package main;

import java.util.LinkedList;
import java.util.List;
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
    private GameObject mParent;
    private Transform mTransform;
    private AAppearance mAppearance;
    private Kinematics mKinematics;

    private List<GameObject> mChildren = new LinkedList<>();

    // TODO: add object for game event handlers

    /**
     * Package-private constructor.
     *
     * @param pId the game object's unique id
     * @param pZIndex the z-index used to determine update and render order
     * @param pTag the tag identifier, not necessarily unique
     * @param pParent the optional parent of this game object
     * @param pTransform the transform containing the game object's position, rotation, and scale
     * @param pAppearance the appearance of the game object, how it's rendered
     * @param pKinematics the kinematics of the game object describing its motion
     */
    GameObject(int pId, int pZIndex, String pTag, GameObject pParent, Transform pTransform, AAppearance pAppearance, Kinematics pKinematics) {
        mId = pId;
        mZIndex = pZIndex;
        mTag = pTag;
        mParent = pParent;
        mTransform = pTransform;
        mAppearance = pAppearance;
        mKinematics = pKinematics;

        if (mParent != null) {
            mParent.addChild(this);
        }
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
     * @return the parent
     */
    public Optional<GameObject> getParent() {
        return Optional.ofNullable(mParent);
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
     * Sets the parent.
     *
     * @param pParent the parent to be set to, can be null
     */
    public void setParent(GameObject pParent) {
        // Update previous parent
        if (mParent != null) {
            if (!mParent.removeChild(this)) {
                Debug.error("GameObject with id " + mId
                        + " improperly bound to parent with id " + mParent.getId());
            }
        }

        mParent = pParent;

        // Update new parent
        if (mParent != null) {
            mParent.addChild(this);
        }
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

    /**
     * Adds a child of this game object.
     *
     * @param pChild the child
     */
    private void addChild(GameObject pChild) {
        mChildren.add(pChild);
    }

    /**
     * Removes a child of this game object.
     *
     * @param pChild the child
     * @return whether the child was successfully removed
     */
    private boolean removeChild(GameObject pChild) {
        return mChildren.remove(pChild);
    }

    /**
     * @return the list of children of this game object
     */
    List<GameObject> getChildren() {
        return mChildren;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + mId;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object pObj) {
        if (!(pObj instanceof GameObject)) {
            return false;
        }

        return mId == ((GameObject)pObj).getId();
    }

}
