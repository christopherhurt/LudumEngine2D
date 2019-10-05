package main;

import java.awt.geom.Point2D;
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

    /** The default resolve transform, which won't have any effect on the resolving game object */
    private static final Transform DEFAULT_RESOLVE_TRANSFORM = new Transform(0d, 0d, 1d, 1d);

    private int mId;
    private int mZIndex;
    private String mTag;
    private GameObject mParent;
    private IHandler mHandler;
    private Transform mTransform;
    private AAppearance mAppearance;
    private Kinematics mKinematics;

    private List<GameObject> mChildren = new LinkedList<>();
    private Transform mResolvedTransform = null;

    /**
     * Package-private constructor.
     *
     * @param pId the game object's unique id
     * @param pZIndex the z-index used to determine update and render order
     * @param pTag the tag identifier, not necessarily unique
     * @param pParent the optional parent of this game object
     * @param pHandler the handler for all event types
     * @param pTransform the transform containing the game object's position, rotation, and scale
     * @param pAppearance the appearance of the game object, how it's rendered
     * @param pKinematics the kinematics of the game object describing its motion
     */
    GameObject(int pId, int pZIndex, String pTag, GameObject pParent, IHandler pHandler, Transform pTransform,
               AAppearance pAppearance, Kinematics pKinematics) {
        mId = pId;
        mZIndex = pZIndex;
        mTag = pTag;
        mParent = pParent;
        mHandler = pHandler;
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
     * @return the event handler
     */
    public Optional<IHandler> getHandler() {
        return Optional.ofNullable(mHandler);
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
     * Sets the handler component.
     *
     * @param pHandler the handler to be set to
     */
    public void setHandler(IHandler pHandler) {
        mHandler = pHandler;
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
     * @return the list of children of this game object
     */
    List<GameObject> getChildren() {
        return mChildren;
    }

    /**
     * @return the resolved transform, possibly empty
     */
    Optional<Transform> getResolvedTransform() {
        return Optional.ofNullable(mResolvedTransform);
    }

    /**
     * Resolves the overall screen space transform for this game object from the parent object.
     */
    void resolveTransformFromParent() {
        Transform parentResolvedTransform;
        if (mParent == null) {
            // No parent, use default resolve transform
            parentResolvedTransform = DEFAULT_RESOLVE_TRANSFORM;
        } else {
            parentResolvedTransform = mParent.mResolvedTransform;
        }

        if (getTransform().isPresent()) {
            Transform transform = getTransform().get();
            mResolvedTransform = new Transform(
                    transform.getX() * parentResolvedTransform.getScaleX() + parentResolvedTransform.getX(),
                    transform.getY() * parentResolvedTransform.getScaleY() + parentResolvedTransform.getY(),
                    transform.getScaleX() * parentResolvedTransform.getScaleX(),
                    transform.getScaleY() * parentResolvedTransform.getScaleY());
        } else {
            // No transform on this game object, pass on the parent's resolved transform
            mResolvedTransform = parentResolvedTransform.copy();
        }
    }

    /**
     * @return an optional containing the normalized location of the mouse cursor relative to this game object
     *         relative to the dimension of this game object and its top-left corner, or an empty optional if this game
     *         object does not have a Transform component attached
     */
    public Optional<Point2D.Double> getRelativeMouseLocation(AInputEvent pEvt) {
        Optional<Point2D.Double> worldMouseLocation = pEvt.getWorldMouseLocation();

        if (worldMouseLocation.isPresent()) {
            if (getTransform().isPresent() && getResolvedTransform().isPresent()) {
                Transform transform = getResolvedTransform().get();
                double normalizedX = (worldMouseLocation.get().getX() - transform.getX())
                        / transform.getScaleX() + 0.5;
                double normalizedY = (worldMouseLocation.get().getY() - transform.getY())
                        / transform.getScaleY() + 0.5;
                return Optional.of(new Point2D.Double(normalizedX, normalizedY));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
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
