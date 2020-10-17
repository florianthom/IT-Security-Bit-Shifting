package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import org.junit.runner.RunWith;

import itsecruity.BigNumber;
import itsecruity.enums.OutputFormat;
import testDriver.GetTests;


public class ArithmeticTest extends Lib4Tests {
	
	
    final private String FILENAME = "Arithmetic-Tests.txt";

    private GetTests tests = null;

    public ArithmeticTest() {
        
    }
    

    
    @Test
    public void testAddition()
    {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
            
//            if(!onlyTestCase(cur,"Arith-HexDec-25")) {
//                continue;
//            }
            
            BigNumber a = new BigNumber(Short.valueOf(cur.get("s")),cur.get("a"));
            BigNumber b = new BigNumber(Short.valueOf(cur.get("s")),cur.get("b"));
            BigNumber c = new BigNumber(Short.valueOf(cur.get("s")),0);
            
            
            
            BigNumber.add(c, a, b);
            String result = c.toString16(OutputFormat.allHex);
            
            // parameter got changed (i use junit 4)
            // -> because of that the proper parameter list is: msg, expected, actual          
            assertEquals( "addition test" + " lineNo=" + cur.get("Line"), cur.get("+"), result);
        }
        tests= null;
    }
    
    @Test
    public void testSubtraction()
    {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
//            if(!onlyTestCase(cur,"Arith-HexDec-27")) {
//                continue;
//            }
            

            BigNumber a = new BigNumber(Short.valueOf(cur.get("s")),cur.get("a"));
            BigNumber b = new BigNumber(Short.valueOf(cur.get("s")),cur.get("b"));
            BigNumber c = new BigNumber(Short.valueOf(cur.get("s")),0);
            
            BigNumber.sub(c, a, b);
            
            String result = c.toString16(OutputFormat.allHex);
            assertEquals("subtraction test" + " lineNo=" + cur.get("Line"), cur.get("-"), result);
        }
        tests= null;
    }
    
    @Test
    public void testMultiplication()
    {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
//            if(!onlyTestCase(cur,"Arith-HexDec-78")) {
//                continue;
//            }

            BigNumber a = new BigNumber(Short.valueOf(cur.get("s")),cur.get("a"));
            BigNumber A = new BigNumber(Short.valueOf(cur.get("s")),cur.get("A"));
            assertEquals("convert to dec test" + " lineNo=" + cur.get("Line"), a.toString16(), A.toString16());
            
            BigNumber b = new BigNumber(Short.valueOf(cur.get("s")),cur.get("b"));
            BigNumber B = new BigNumber(Short.valueOf(cur.get("s")),cur.get("B"));
            assertEquals("convert to dec test" + " lineNo=" + cur.get("Line"), b.toString16(), B.toString16());
            
            BigNumber c = new BigNumber(Short.valueOf(cur.get("s")),0);
            
            BigNumber.mul(c, a, b);
            
            String result = c.toString16(OutputFormat.allHex);

            assertEquals("multiplication test" + " lineNo=" + cur.get("Line"), cur.get("*"), result);
        }
        tests= null;
    }
    
    @Test
    public void testDivision()
    {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
//            if(onlyTestCase(cur,"Arith-HexDec-10"))
//            {
//                continue;
//            }
            if(cur.get("/").equals("")) {
                continue;
            }
            
            
            BigNumber a = new BigNumber(Short.valueOf(cur.get("s")),cur.get("a"));
            BigNumber A = new BigNumber(Short.valueOf(cur.get("s")),cur.get("A"));
            assertEquals("convert to dec test" + " lineNo=" + cur.get("Line"), a.toString16(), A.toString16());
            
            BigNumber b = new BigNumber(Short.valueOf(cur.get("s")),cur.get("b"));
            BigNumber B = new BigNumber(Short.valueOf(cur.get("s")),cur.get("B"));
            assertEquals("convert to dec test" + " lineNo=" + cur.get("Line"), b.toString16(), B.toString16());

            BigNumber div = new BigNumber(Short.valueOf(cur.get("s")),0);
            BigNumber mod= BigNumber.div(div, a, b);
            
            String divValue = div.toString16(OutputFormat.allHex);
            String modValue = mod.toString16(OutputFormat.allHex);
            
            assertEquals("Division test /" + " lineNo=" + cur.get("Line"), reduceHexString(cur.get("/")), reduceHexString(divValue));
            assertEquals("Division test %" + " lineNo=" + cur.get("Line"), reduceHexString(cur.get("%")), reduceHexString(modValue));
        }
        tests= null;
    }
    
    @Test
    public void testDivisionBy0()
    {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
            if(!cur.get("/").equals("")) {
                continue;
            }


            BigNumber a = new BigNumber(Short.valueOf(cur.get("s")),cur.get("a"));
            BigNumber A = new BigNumber(Short.valueOf(cur.get("s")),cur.get("A"));
            
            
            
            assertEquals("convert to dec test" + " lineNo=" + cur.get("Line"), a.toString16(), A.toString16());
            BigNumber b = new BigNumber(Short.valueOf(cur.get("s")),cur.get("b"));
            BigNumber B = new BigNumber(Short.valueOf(cur.get("s")),cur.get("B"));
            assertEquals("convert to dec test" + " lineNo=" + cur.get("Line"), b.toString16(), B.toString16());
            
            BigNumber div = new BigNumber(Short.valueOf(cur.get("s")),0);
            try {
            	BigNumber mod= BigNumber.div(div, a, b);
                fail("missing IllegalArgumentException");
            } catch (IllegalArgumentException e) {
                
            }
            
        }
        tests= null;
    }
}