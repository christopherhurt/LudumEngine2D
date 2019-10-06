package main;

import java.awt.geom.Point2D;
import java.util.*;

/**
 * Represents a game object, the basic unit of the game object. Components can be added to each game object individually
 * to give each one properties like locational data, appearance, event handlers, etc.
 *
 * @author Chris Hurt
 * @version 10.03.19
 */
public class GameObject {

    /** The default resolve transform, which won't have any effect on the resolving game object */
    private static final Transform DEFAULT_RESOLVE_TRANSFORM = new Transform(0d, 0d, 1d, 1d);

    /** Tracks unique identifiers for debugging purposes */
    private static int sIdCounter = 0;

    private GameObject mParent;
    private int mZIndex;
    private int mId;

    private String mTag = null;
    private IHandler mHandler = null;
    private Transform mTransform = null;
    private AAppearance mAppearance = null;
    private Kinematics mKinematics = null;
    private AGUIComponent mGUIComponent = null;
    private BoundingBox mBoundingBox = null;

    private List<GameObject> mChildren = new LinkedList<>();
    private Transform mResolvedTransform = null;

    /**
     * Default constructor.
     */
    protected GameObject() {
        this(null, 0);
    }

    /**
     * Constructor.
     *
     * @param pParent the optional parent of this game object
     */
    protected GameObject(GameObject pParent) {
        this(pParent, 0);
    }

    /**
     * Constructor.
     *
     * @param pParent the optional parent of this game object
     * @param pZIndex the z-index for ordering the game object in the scene
     */
    protected GameObject(GameObject pParent, int pZIndex) {
        mParent = pParent;
        mZIndex = pZIndex;

        mId = sIdCounter++;

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
     * @return the GUI component
     */
    public Optional<AGUIComponent> getGUIComponent() {
        return Optional.ofNullable(mGUIComponent);
    }

    /**
     * @return the bounding box component
     */
    public Optional<BoundingBox> getBoundingBox() {
        return Optional.ofNullable(mBoundingBox);
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
    public void attachTag(String pTag) {
        mTag = pTag;
    }

    /**
     * Sets the handler component.
     *
     * @param pHandler the handler to be set to
     */
    public void attachHandler(IHandler pHandler) {
        mHandler = pHandler;
    }

    /**
     * Sets the transform component.
     *
     * @param pTransform the transform to be set to
     */
    public void attachTransform(Transform pTransform) {
        mTransform = pTransform;
    }

    /**
     * Sets the appearance component.
     *
     * @param pAppearance the appearance to be set to
     */
    public void attachAppearance(AAppearance pAppearance) {
        if (mTransform == null) {
            Debug.error("Cannot attach appearance component to GameObject with id "
                    + mId + " that doesn't have a transform component");
        } else {
            mAppearance = pAppearance;
        }
    }

    /**
     * Sets the kinematics component.
     *
     * @param pKinematics the kinematics to be set to
     */
    public void attachKinematics(Kinematics pKinematics) {
        if (mTransform == null) {
            Debug.error("Cannot attach kinematics component to GameObject with id "
                    + mId + " that doesn't have a transform component");
        } else {
            mKinematics = pKinematics;
        }
    }

    /**
     * Sets the GUI component.
     *
     * @param pGUIComponent the GUI component to be set to
     */
    public void attachGUIComponent(AGUIComponent pGUIComponent) {
        if (mTransform == null) {
            Debug.error("Cannot attach GUI component to GameObject with id "
                    + mId + " that doesn't have a transform component");
        } else {
            mGUIComponent = pGUIComponent;
        }
    }

    /**
     * Sets the bounding box component.
     *
     * @param pBoundingBox the bounding box component to be set to
     */
    public void attachBoundingBox(BoundingBox pBoundingBox) {
        if (mTransform == null) {
            Debug.error("Cannot attach bounding box component to GameObject with id "
                    + mId + " that doesn't have a transform component");
        } else {
            mBoundingBox = pBoundingBox;
        }
    }

    /**
     * Adds a child of this game object.
     *
     * @param pChild the child
     */
    public void addChild(GameObject pChild) {
        mChildren.add(pChild);
        pChild.mParent = this;
    }

    /**
     * Removes a child from this game object's list of children.
     *
     * @param pChild the child game object to remove
     */
    public void removeChild(GameObject pChild) {
        if (mChildren.remove(pChild)) {
            pChild.mParent = null;
        } else {
            Debug.warn("Failed to remove child GameObject with id "
                    + pChild.mId + " from parent GameObject with id " + mId);
        }
    }

    /**
     * Clears the list of children to this game object.
     */
    public void clearChildren() {
        Iterator<GameObject> childIterator = mChildren.iterator();
        while (childIterator.hasNext()) {
            childIterator.next().mParent = null;
            childIterator.remove();
        }
    }

    /**
     * @return the unmodifiable list of children of this game object
     */
    public List<GameObject> getChildrenUnmodifiable() {
        return Collections.unmodifiableList(mChildren);
    }

    /**
     * @return the resolved transform, possibly empty
     */
    public Optional<Transform> getResolvedTransform() {
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
