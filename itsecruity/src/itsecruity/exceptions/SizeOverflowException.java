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
public class SizeOverflowException extends RuntimeException {

    /**
     * Creates a new instance of <code>SizeOverflowException</code> without
     * detail message.
     */
    SizeOverflowException() {
    }

    /**
     * Constructs an instance of <code>SizeOverflowException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SizeOverflowException(final String msg) {
        super(msg);
    }

}
