package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.runner.RunWith;
import itsecruity.*;
import itsecruity.enums.OutputFormat;
import itsecruity.exceptions.SizeOverflowException;

/**
 *
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */
public class ConvertHexTest extends Lib4Tests {

    final private String FILENAME = "Convert-Hex-Tests.txt";

    private GetTests tests = null;

    public ConvertHexTest() {
    }
    
    
    
    
//    @Disabled
    @Test
    public void testHex2Hex() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
//        System.out.println("####### test "+2048+" convert hex to hex");
//            System.out.println(cur);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
            
            
        
            
            
//            short sz = Short.valueOf(cur.get("s"));
            short sz = 2048;
            
            

            
            
            BigInt bi = new BigInt(sz, 0);
            bi.toBigInt(cur.get("h"));
            String result = bi.toString16(OutputFormat.hex);
            
            System.out.println(cur.get("h"));
            
            // glaube (expected, actual, )
//            assertEquals(cur.get("h"), result, "hex to hex test" + " lineNo=" + cur.get("Line"));
            assertEquals(cur.get("h"), result);

        }
    }
    
    
    
    
    
    
//    @Disabled
    @Test
    public void testDec2Hex() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
//        System.out.println("####### test "+2048+" convert hex to dec");
//            System.out.println(cur);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
            short sz = 2048;
//            if(!onlyTestCase(cur,"dec-Hex-7")) {
//                continue;
//            }
//            System.out.println("        test "+cur.get("t"));
            BigInt bi = new BigInt(sz, 0);
            bi.toBigInt(cur.get("d"));
            String result = bi.toString16(OutputFormat.hex);
            assertEquals(cur.get("h"), result, "hex to dec test" + " lineNo=" + cur.get("Line"));
        }
    }
    
//    @Disabled
    @Test
    public void testDec2Dec() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
//        System.out.println("####### test "+2048+" convert dec to dec");
//            System.out.println(cur);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
            short sz = 2048;
//            if(!onlyTestCase(cur,"dec-Hex-11")) {
//                continue;
//            }
//            System.out.println("        test "+cur.get("t"));
            BigInt bi = new BigInt(sz, 0);
            bi.toBigInt(cur.get("d"));
            String result = bi.toString10();
            assertEquals(cur.get("d"), result, "dec to dec test" + " lineNo=" + cur.get("Line"));
        }
    }

    @Test
    public void testHexDecOverflow() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
//        System.out.println("####### test "+256+" convert hex to dec");
//            System.out.println(cur);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
            short sz = Short.valueOf(cur.get("s"));     //allocate 256 bit
            // test 14 must throw an exception
            if(!onlyTestCase(cur,"dec-Hex-14")) { 
                continue;
            }
//            System.out.println("        test "+cur.get("t"));
            BigInt bi;
            try {
                bi = new BigInt(sz, 0);
                bi.toBigInt(cur.get("d"));
                fail("missing SizeOverflowException");
            } catch (SizeOverflowException e) {
                
            }
        }
    }
}
