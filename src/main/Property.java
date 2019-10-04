package main;

/**
 * Simple wrapper class for a generic object. This is useful for modifying fields in lambda functions, such as a
 * game object handler. This class was designed as a lightweight alternative to Java Reference objects.
 *
 * @param <T> the property type being stored
 *
 * @author Chris Hurt
 * @version 10.04.19
 */
public final class Property<T> {

    private T mValue;

    /**
     * Default constructor.
     */
    public Property() {
        this(null);
    }

    /**
     * Constructor.
     *
     * @param pValue the property value
     */
    public Property(T pValue) {
        mValue = pValue;
    }

    /**
     * @return the property value, can be null
     */
    public T get() {
        return mValue;
    }

    /**
     * Sets the property value.
     *
     * @param pValue the property value to be set to
     */
    public void set(T pValue) {
        mValue = pValue;
    }

    /**
     * @return whether the property value is present, i.e. not null
     */
    public boolean isPresent() {
        return mValue != null;
    }

}
