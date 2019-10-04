package main;

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
     */
    void handle(AEvent pEvt);

}
