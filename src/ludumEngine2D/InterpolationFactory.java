package ludumEngine2D;

import java.util.Collection;
import java.util.HashSet;

/**
 * Factory class for creating interpolations. Interpolation constructors are obscured from the user of the engine to
 * allow interpolations to be registered for value updates once per frame.
 *
 * @author Chris Hurt
 * @version 10.05.19
 */
public final class InterpolationFactory {

    /** The collection of registered interpolations. Values will be updated every frame */
    private static final Collection<InterpolatedDouble> INTERPOLATIONS = new HashSet<>();

    /**
     * Creates and registers an InterpolatedDouble object. These will be updated once per frame.
     *
     * @param pName the identifying name of the interpoaltion
     * @param pInitialValue the initial value
     * @param pFinalValue the final value
     * @param pDuration the duration of the interpolation from initial to final value in seconds
     * @return the new InterpolatedDouble object
     */
    public static InterpolatedDouble createInterpolatedDouble(String pName, double pInitialValue, double pFinalValue,
                                                              double pDuration) {
        InterpolatedDouble interpolation = new InterpolatedDouble(pName, pInitialValue, pFinalValue, pDuration);
        INTERPOLATIONS.add(interpolation);
        return interpolation;
    }

    /**
     * Updates all registered interpolations, called once per frame. Note that all registered interpolations will be
     * updated, regardless of the current scene.
     */
    static void updateAll() {
        INTERPOLATIONS.forEach(InterpolatedDouble::update);
    }

    private InterpolationFactory() {
        // Static class, do not initialize
    }

}
