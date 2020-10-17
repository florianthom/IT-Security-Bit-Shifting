///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package itsecruity;
//
//import itsecruity.enums.signt;
//import itsecruity.enums.signt2;
//
///**
// *
// * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
// */
//public class BigInt extends BigNumber {
//    
//    BigInt() {
//        super();
//    }
//    
//    
//    BigInt(String str) {
//        super(str); 
//    }
//    
//    
//    
//    BigInt(int value) {
//        super(value); 
//    }
//    
//    
//    
//    public BigInt(short size, String str) {
//        super(size,str); 
//    }
//    
//    
//    
//    
//    public BigInt(short size, int value) {
//        super(size,value); 
//    }
//    
//    
//    
//    public static void assign(BigInt a, BigInt b) {
//        // a:= b without clone or copy          
//    }
//
//    
//    
//
//    
//    
//    
//    
//
//    
//    
//    
//    
//
//    private static short relevant(BigInt a) {
//        // the higher part above spart
//        return 0;
//    }
//    
//    
//    
//    
//    
//
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    private static void checkSize(BigInt a, short maxSize) {
//        // maxSize max. number of cells
//    }
//
//    
//    
//
//    
//    
//    
//    public static void _increment(BigInt c, int b) {
//        // no processing of the signs, all values are interpreted to be positive
//        // otherwise there is addInt() necessary
//        
//    }
//    
//    
//    
//
//    
//    
//    
//    
//
//    
//    
//    
//
//    
//    //----------------------------------
//    /**
//     * division operations
//     * @param div
//     * @param c
//     * @param a
//     * @param b
//     * @return 
//     */
//
//    public static BigInt div(BigInt div, BigInt a, BigInt b) {
//        // div:= a // b
//        // return mod= a%b
//
//        return null;
//    }
//    
//    
//    
//    
////    private static int estimateKnuth(int cUpper, int cLower, int divisor) {
////        return 0;
////    }
//    private static int estimateAlter(int cUpper, int cLower, int divisor) {
//        // all three parameters are cells (16 bit);
//        return 0;    
//    }
//    
//    
//    
//    
//    
//    private static BigInt _div(BigInt div, BigInt a, BigInt b) {
//        // no processing of the signs, all values are interpreted to be positive
//        // div:= a div b, a mod b
//        
//        return null;
//    }
//    
//    
//    
//    
//    /**
//     * compare functions
//     * No allocation of a, b
//     * @param a
//     * @param b
//     * @return 
//     */
//    private static boolean _lt(BigInt a, BigInt b) {
//        // compare absolute values, ignore sign
//        // do not change spart
//        
//        return false;
//    }
//    
//    
//    
//    
//
//    
//    
//    
//    public static boolean lt0(BigInt a) {
//        // evaluate sign
//        return a.sign==signt.negative;    // a<b
//    }
//    
//    
//    
//    
//    private static boolean _eq(BigInt a, BigInt b) {
//        // compare absolute values, ignore sign
//        // do not change spart
//        return true;    // a==b
//    }
//    
//    
//    
//    public static boolean eq0(BigInt a) {
//        // dont correct -0 to 0
//        return false;
//    }
//}
