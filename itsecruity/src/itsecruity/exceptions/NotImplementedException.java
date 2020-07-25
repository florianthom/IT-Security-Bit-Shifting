/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itsecruity.exceptions;

/**
 *
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */
public class NotImplementedException extends RuntimeException {

    /**
     * Creates a new instance of <code>NotImplementedException</code> without
     * detail message.
     */
    public NotImplementedException() {
        super("function is not implemented yet");
    }

    /**
     * Constructs an instance of <code>NotImplementedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotImplementedException(String msg) {
        super(msg);
    }
}
