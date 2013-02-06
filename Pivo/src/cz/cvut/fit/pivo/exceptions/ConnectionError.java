/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.pivo.exceptions;

/**
 *
 * @author Adam
 */
public class ConnectionError extends Exception {

    /**
     * Creates a new instance of
     * <code>ConnectionError</code> without detail message.
     */
    public ConnectionError() {
    }

    /**
     * Constructs an instance of
     * <code>ConnectionError</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ConnectionError(String msg) {
        super(msg);
    }
}
