package itsecruity;

import itsecruity.config.BigIntConfiguration;
import itsecruity.enums.OutputFormat;
import itsecruity.enums.signt;

public final class BigIntMain {
	
	
    private BigIntMain()
    {
    	
    }

    // Numbers: 0 2 65535 4294967295 281474976710656
    public static void main(final String[] args) throws Exception {

    	BigNumber test = new BigNumber("10");
    	System.out.println(test.toString10());
    }
    
    
    public static void testDiv() throws Exception
    {
    	BigNumber q = new BigNumber("0");
    	BigNumber dividend = new BigNumber("281474976710656"); // er shifted irgendwie nicht um 16 bits sondern nur um 4 bits
    	BigNumber divisor = new BigNumber("281474976710657");
    	System.out.println("INIT: printInCellValues q: " + BigNumber.printBigNumberInCellValues(q));
    	System.out.println("INIT: printInCellValues dividend: " + BigNumber.printBigNumberInCellValues(dividend));
    	System.out.println("INIT: printInCellValuesInDual dividend: " + BigNumber.printBigNumbersDual(dividend));
    	System.out.println("INIT: printInCellValues divisor: " + BigNumber.printBigNumberInCellValues(divisor));

    	BigNumber r = BigNumber._divmod(q, dividend, divisor, signt.positive, false);
    	
    	System.out.println("AFTER: printInCellValues r: " + BigNumber.printBigNumberInCellValues(r));
    	System.out.println("AFTER: printInCellValues q: " + BigNumber.printBigNumberInCellValues(q));
    	System.out.println("AFTER: printInCellValueDuals q: " + BigNumber.printBigNumbersDual(q));
        System.out.println("spart: " + q.spart);
    }
    
    public static void testAdd() throws Exception
    {
    	BigNumber a = new BigNumber("0");
    	System.out.println(BigNumber.printBigNumbersDual(a));
    	System.out.println(a.toString10());
    	System.out.println(a.toString16(OutputFormat.allHex));
    	System.out.println(a.toStringSign());
    	
    	//should be in hex: ff0200
    }
    
    public static void testMulVitro() throws Exception
    {
    	// 11907858797062763600287 // 65535 (spart=1) // 18446744073709551616 (spart=5) // 281474976710656 (spart=4) // 4294967296 (spart=3) // 4294967295 (spart==2) (funktioniert max)
    	BigNumber a = new BigNumber("4294967295");
    	System.out.println("a dual: " + BigNumber.printBigNumbersDual(a));
    	BigNumber b = new BigNumber("4294967295");
    	BigNumber c = new BigNumber("0");
    	
    	BigNumber.mul(c, a, b);
    	
    	
    	BigNumber shouldBe = new BigNumber("18446744065119617025"); // 18446744073709551616 // 18446744065119617025 (4294967295)
    	System.out.println("result c in dual:         " + BigNumber.printBigNumbersDual(c));
    	System.out.println("result should be in dual: " + BigNumber.printBigNumbersDual(shouldBe));
    	System.out.println("result c in deci:         " + c.toString10());
    	System.out.println("result should be in deci: " + shouldBe.toString10());
    	System.out.println("result c in cellValues:         " + BigNumber.printBigNumberInCellValues(c));
    	System.out.println("result should be in cellValues: " + BigNumber.printBigNumberInCellValues(shouldBe));
    	
    }
    
    public static void testAddCell2() throws Exception
    {
    	int test1 = 2147483647;
    	BigNumber container = new BigNumber("2147483647");
    	System.out.println("init test1: " + BigNumber.printBigNumbersDual(new BigNumber(test1)));
    	int index = 0;
    	BigNumber.addCell2(container, index, test1);
    	
    	BigNumber shouldBe = new BigNumber("4294967294");
    	System.out.println(container.toString10());
    	System.out.println("should be: " + shouldBe.toString10() );
    	System.out.println(BigNumber.printBigNumbersDual(container));
    	System.out.println(BigNumber.printBigNumbersDual(shouldBe));
    }
    
    
    public static void testAddCell22() throws Exception
    {
    	int test1 = 65535;
    	BigNumber container = new BigNumber("1");
    	System.out.println("init test1: " + BigNumber.printBigNumbersDual(new BigNumber(test1)));
    	int index = 0;
    	BigNumber shouldBe = new BigNumber("65536");
    	BigNumber.addCell2(container, index, test1);
    	
    	System.out.println("start evaluate");
    	System.out.println("result is: " + container.toString10());
    	System.out.println("should be: " + shouldBe.toString10() );
    	System.out.println(BigNumber.printBigNumbersDual(container));
    	System.out.println(BigNumber.printBigNumbersDual(shouldBe));
    }
    
    
    public static void testMul() throws Exception
    {
    	BigNumber a = new BigNumber("609258644980293364");
    	BigNumber b = new BigNumber("11907858797062763600287");
    	BigNumber c = new BigNumber("0");
    	
    	BigNumber.mul(c, a, b);
    	
    	
    	BigNumber shouldBe = new BigNumber("7254965915315125492213065479690094595468");
    	System.out.println("result c in dual:         " + BigNumber.printBigNumbersDual(c));
    	System.out.println("result should be in dual: " + BigNumber.printBigNumbersDual(shouldBe));
    	System.out.println("result c in deci:         " + c.toString10());
    	System.out.println("result should be in deci: " + shouldBe.toString10());
    }
}
