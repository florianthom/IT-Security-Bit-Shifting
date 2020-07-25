package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import org.junit.runner.RunWith;

import itsecruity.BigInt;
import itsecruity.enums.OutputFormat;

/**
 *
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */
@SuppressWarnings({"checkstyle:javadocvariable", "checkstyle:javadocmethod"})
public class ArithmeticTest extends Lib4Tests {
	
	
    final private String FILENAME = "Arithmetic-Tests.txt";

    private GetTests tests = null;

    public ArithmeticTest() {
        
    }
    
//    @Disabled
    @Test
    public void testAddition() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
//            if(!onlyTestCase(cur,"Arith-HexDec-27")) {
//                continue;
//            }
            
//            System.out.println("####### test "+cur.get("t")+" addition");
//            System.out.println(cur);
            BigInt a = new BigInt(Short.valueOf(cur.get("s")),cur.get("a"));
            BigInt b = new BigInt(Short.valueOf(cur.get("s")),cur.get("b"));
            BigInt c = new BigInt(Short.valueOf(cur.get("s")),0);
            BigInt.add(c, a, b);
            
            String result = c.toString16(OutputFormat.allHex);
//            prt("a-value",reduceHexString(cur.get("a")));
//            prt("b-value",reduceHexString(cur.get("b")));
//            prt("c-value",reduceHexString(result));
//            prt("S-value",reduceHexString(cur.get("+")));
            assertEquals(cur.get("+"), result, "addition test" + " lineNo=" + cur.get("Line"));
        }
        tests= null;
    }
    
//    @Disabled
    @Test
    public void testSubtraction() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
//            if(!onlyTestCase(cur,"Arith-HexDec-27")) {
//                continue;
//            }
            
//            System.out.println("####### test "+cur.get("t")+" subtraction");
//            System.out.println(cur);
            BigInt a = new BigInt(Short.valueOf(cur.get("s")),cur.get("a"));
            BigInt b = new BigInt(Short.valueOf(cur.get("s")),cur.get("b"));
            BigInt c = new BigInt(Short.valueOf(cur.get("s")),0);
            BigInt.sub(c, a, b);
            
            String result = c.toString16(OutputFormat.allHex);
//            prt("a-value",reduceHexString(cur.get("a")));
//            prt("b-value",reduceHexString(cur.get("b")));
//            prt("c-value",reduceHexString(result));
//            prt("S-value",reduceHexString(cur.get("-")));
            assertEquals(cur.get("-"), result, "subtraction test" + " lineNo=" + cur.get("Line"));
        }
        tests= null;
    }
    
    @Test
    public void testMultiplication() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
//            if(!onlyTestCase(cur,"Arith-HexDec-35")) {
//                continue;
//            }
            
//            System.out.println("####### test "+cur.get("t")+" multiplication");
//            System.out.println(cur);
            BigInt a = new BigInt(Short.valueOf(cur.get("s")),cur.get("a"));
            BigInt A = new BigInt(Short.valueOf(cur.get("s")),cur.get("a"));
            assertEquals(a.toString16(), A.toString16(), "convert to dec test" + " lineNo=" + cur.get("Line"));
            BigInt b = new BigInt(Short.valueOf(cur.get("s")),cur.get("b"));
            BigInt B = new BigInt(Short.valueOf(cur.get("s")),cur.get("b"));
            assertEquals(b.toString16(), B.toString16(), "convert to dec test" + " lineNo=" + cur.get("Line"));
            
            BigInt c = new BigInt(Short.valueOf(cur.get("s")),0);
            BigInt.mul(c, a, b);
            
            String result = c.toString16(OutputFormat.allHex);
//            BigNumber.prt("a-value",reduceHexString(cur.get("a")));
//            BigNumber.prt("b-value",reduceHexString(cur.get("b")));
//            BigNumber.prt("result ",reduceHexString(result));
//            BigNumber.prt("S-value",reduceHexString(cur.get("*")));
            assertEquals(cur.get("*"), result, "multiplication test" + " lineNo=" + cur.get("Line"));
        }
        tests= null;
    }
    
//    @Disabled
    @Test
    public void testDivision() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
//            if(!onlyTestCase(cur,"Arith-HexDec-40")) {
//                continue;
//            }
            if(cur.get("/").equals("")) {   // skip division by 0
                continue;
            }
//            System.out.println("####### test "+cur.get("t")+" Division");
//            System.out.println(cur);
            BigInt a = new BigInt(Short.valueOf(cur.get("s")),cur.get("a"));
            BigInt A = new BigInt(Short.valueOf(cur.get("s")),cur.get("a"));
            assertEquals(a.toString16(), A.toString16(), "convert to dec test" + " lineNo=" + cur.get("Line"));
            BigInt b = new BigInt(Short.valueOf(cur.get("s")),cur.get("b"));
            BigInt B = new BigInt(Short.valueOf(cur.get("s")),cur.get("b"));
            assertEquals(b.toString16(), B.toString16(), "convert to dec test" + " lineNo=" + cur.get("Line"));
            
            BigInt div = new BigInt(Short.valueOf(cur.get("s")),0);
            BigInt mod= BigInt.div(div, a, b);
            
            String divValue = div.toString16(OutputFormat.allHex);
            String modValue = mod.toString16(OutputFormat.allHex);
//            BigNumber.prt("a-value",reduceHexString(cur.get("a")));
//            BigNumber.prt("b-value",reduceHexString(cur.get("b")));
//            BigNumber.prt("div    ",reduceHexString(divValue));
//            BigNumber.prt("mod    ",reduceHexString(modValue));
//            BigNumber.prt("right div",reduceHexString(cur.get("/")));
//            BigNumber.prt("right mod",reduceHexString(cur.get("%")));
            assertEquals(reduceHexString(cur.get("/")), reduceHexString(divValue),
                              "Division test /" + " lineNo=" + cur.get("Line"));
            assertEquals(reduceHexString(cur.get("%")), reduceHexString(modValue),
                              "Division test %" + " lineNo=" + cur.get("Line"));
        }
        tests= null;
    }
    
    @Test
    public void testDivisionBy0() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
//            cur.put("a", correctHex(cur.get("a")));
//            cur.put("b", correctHex(cur.get("b")));
//            cur.put("/", correctHex(cur.get("/")));
//            cur.put("%", correctHex(cur.get("%")));
            if(!cur.get("/").equals("")) {
                continue;
            }
//            System.out.println("####### test "+cur.get("t")+" Division by 0");
//            System.out.println(cur);
            
            
            
            
            
            BigInt a = new BigInt(Short.valueOf(cur.get("s")),cur.get("a"));
            BigInt A = new BigInt(Short.valueOf(cur.get("s")),cur.get("a"));
            
            
            
            assertEquals(a.toString16(), A.toString16(), "convert to dec test" + " lineNo=" + cur.get("Line"));
//            BigInt b = new BigInt(Short.valueOf(cur.get("s")),cur.get("b"));
//            BigInt B = new BigInt(Short.valueOf(cur.get("s")),cur.get("b"));
//            assertEquals(b.toString16(), B.toString16(), "convert to dec test" + " lineNo=" + cur.get("Line"));
            
            
            
//            BigInt div = new BigInt(Short.valueOf(cur.get("s")),0);
//            try {
//                BigInt mod= BigInt.div(div, a, b);
//                fail("missing IllegalArgumentException");
//            } catch (IllegalArgumentException e) {
//                
//            }
            
            
            
        }
        tests= null;
    }
}