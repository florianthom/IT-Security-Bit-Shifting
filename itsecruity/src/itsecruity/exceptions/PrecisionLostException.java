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
public class PrecisionLostException extends RuntimeException {

    /**
     * Creates a new instance of <code>PrecisionLostException</code> without
     * detail message.
     */
    public PrecisionLostException() {
    }

    /**
     * Constructs an instance of <code>PrecisionLostException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PrecisionLostException(String msg) {
        super(msg);
    }

}
