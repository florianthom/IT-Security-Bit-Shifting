/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itsecruity.enums;

/**
 *
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */
public enum signt {
    positive,
    negative;

    @Override
    public String toString() {
        switch (this) {
            case positive:
                return "+";
            case negative:
                return "-";
            default:
                throw new IllegalArgumentException();
        }
    }
}
