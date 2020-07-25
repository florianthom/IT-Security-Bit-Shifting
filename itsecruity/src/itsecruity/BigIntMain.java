package itsecruity;

import itsecruity.enums.OutputFormat;

public final class BigIntMain {
	
	
	
    /**
     * Disable any inheritance.
     */
    private BigIntMain()
    {
    	
    }

    
    public static void main(final String[] args) {

//    	BigNumber b1 = new BigNumber("2");
//    	BigNumber b2 = new BigNumber("65536");
    	BigNumber c = BigNumber.powWithBaseTwo(0);
//    	BigNumber.mul(c, b1, b2);
    	
    	System.out.println(c.value[0]);
    	System.out.println(c.value[1]);
    	System.out.println(c.value[2]);
        System.out.println(c.spart);
        

        
        
        
        
        
        
//        BigInt b2= new BigInt("+0x47753");
//        b2= new BigInt("-0x400000000000000000000000000000000000000000000000003");
//        System.out.println(b2.toString16(OutputFormat.hex));


    }
}
