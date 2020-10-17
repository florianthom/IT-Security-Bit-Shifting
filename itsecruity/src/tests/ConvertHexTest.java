package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.runner.RunWith;
import itsecruity.BigNumber;
import itsecruity.enums.OutputFormat;
import itsecruity.exceptions.SizeOverflowException;
import testDriver.GetTests;

/**
 *
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */
public class ConvertHexTest extends Lib4Tests {

    final private String FILENAME = "Convert-Hex-Tests.txt";

    private GetTests tests = null;

    public ConvertHexTest()
    {
    	
    }
    
    
    
    @Test
    public void testHex2Hex() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
//			if(!onlyTestCase(cur,"dec-Hex-1"))
//			{
//				continue;
//			}
            short sz = 2048;
            
            BigNumber bi = new BigNumber(sz, 0);
            bi.toBigInt(cur.get("h"));
            String result = bi.toString16(OutputFormat.hex);
            
            
            assertEquals("hex to hex test" + " lineNo=" + cur.get("Line"), cur.get("h"), result);
            assertEquals(cur.get("h"), result);
        }
    }
    
    
    
    
    @Test
    public void testDec2Hex() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
            short sz = 2048;
//            if(!onlyTestCase(cur,"dec-Hex-7")) {
//                continue;
//            }
            BigNumber bi = new BigNumber(sz, 0);
            bi.toBigInt(cur.get("d"));
            String result = bi.toString16(OutputFormat.hex);
            assertEquals("hex to dec test" + " lineNo=" + cur.get("Line"), cur.get("h"), result);
        }
    }
    
    @Test
    public void testDec2Dec() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
            short sz = 2048;
//            if(!onlyTestCase(cur,"dec-Hex-1")) {
//                continue;
//            }
            BigNumber bi = new BigNumber(sz, 0);
            bi.toBigInt(cur.get("d"));
            String result = bi.toString10();
            assertEquals("dec to dec test" + " lineNo=" + cur.get("Line"), cur.get("d"), result);
        }
    }

    @Test
    public void testHexDecOverflow() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
            short sz = Short.valueOf(cur.get("s"));     //allocate 256 bit
            // test 14 must throw an exception
            if(!onlyTestCase(cur,"dec-Hex-14")) { 
                continue;
            }
            BigNumber bi;
            try
            {
            	System.out.println("test: " + cur.get("t"));
            	System.out.println("size: " + cur.get("s"));
                bi = new BigNumber(sz, 0);
                bi.toBigInt(cur.get("d"));
                fail("missing SizeOverflowException");
            }
            catch (SizeOverflowException e)
            {
                
            }
        }
    }
}
