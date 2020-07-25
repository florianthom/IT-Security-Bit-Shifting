/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itsecruity.config;

/**
 *
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */
public class BigIntConfiguration {

    public final static short INIT_SIZE = 100;  // min number of cells
    public final static short MAX_SIZE = 6000;  // max number of cells

    
    
    
    // glaube entspricht "base" bei ihm (oder es ist tatsächlich die Anzahl der Benutzen Bits innerhalb der aktuellen Zelle)
    public final static short CELL_SIZE = 16;           // 16 bit cells for 32 bit machines
    
    
    
    public final static int CELL_MASK = 0xFFFF;         // 16 bit mask for 32 bit machines
    public final static int CELL_MASK2 = 0xFFFFFFFF;    // 32 bit mask for 64 bit machines

    public final static short CELL_SIZE_HEX = CELL_SIZE / 4;   // hex number per cell
    
    public final static int LIMIT_KARATSUBA = 0; // 80 benutzt java BigInteger
    
    
    public final static short ARRAY_BIT_SIZE_STATIC = 2048;
}
