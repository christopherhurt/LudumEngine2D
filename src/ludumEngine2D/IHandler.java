package ludumEngine2D;

/**
 * Handler component attachable to game objects. The handle() method can be overridden to handle any type of event.
 * This interface is designed such that the handle() method can easily defined as a lambda expression.
 *
 * @author Chris Hurt
 * @version 10.04.19
 */
public interface IHandler {

    /**
     * Handles an event.
     *
     * @param pEvt the event to handle
     * @param pSelf the owner of this handler
     */
    void handle(AEvent pEvt, GameObject pSelf);

}
