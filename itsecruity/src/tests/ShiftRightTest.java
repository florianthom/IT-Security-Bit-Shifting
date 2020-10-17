package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import org.junit.runner.RunWith;

import itsecruity.BigNumber;
import itsecruity.enums.OutputFormat;
import testDriver.GetTests;

/**
 *
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */
public class ShiftRightTest extends Lib4Tests
{

    private final static String FILENAME = "Shift-Right-Tests.txt";

    private static GetTests tests = null;

    public ShiftRightTest()
    {
        
    }
    
    @Before
    public void setUp() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
    }
    
    @After
    public void tearDown() {
        tests = null;
    }

    @Test
    public void testShiftLeft() {
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            String result;
            HashMap<String, String> cur = ti.next();
//            if(!onlyTestCase(cur,"ShiftRight-5")){
//                continue;
//            }
            
            BigNumber a = new BigNumber(Short.valueOf(cur.get("s")),cur.get("a"));
            
            BigNumber.shiftRight(a, 1);
            
            result = a.toString16(OutputFormat.allHex);
            
            assertEquals("ShiftLeft test b" + " lineNo=" + cur.get("Line"), cur.get("b"), result);
            
            BigNumber.shiftRight(a, 1);
            result = a.toString16(OutputFormat.allHex);
            assertEquals("ShiftLeft test c" + " lineNo=" + cur.get("Line"), cur.get("c"), result);
            
            BigNumber.shiftRight(a, 1);
            result = a.toString16(OutputFormat.allHex);
            assertEquals("ShiftLeft test d" + " lineNo=" + cur.get("Line"), cur.get("d"), result);
            
            BigNumber.shiftRight(a, 1);
            result = a.toString16(OutputFormat.allHex);
            assertEquals("ShiftLeft test e" + " lineNo=" + cur.get("Line"), cur.get("e"), result);
            
            BigNumber.shiftRight(a, 1);
            result = a.toString16(OutputFormat.allHex);
            assertEquals("ShiftLeft test f" + " lineNo=" + cur.get("Line"), cur.get("f"), result);
            
            BigNumber.shiftRight(a, 1);
            result = a.toString16(OutputFormat.allHex);
            assertEquals("ShiftLeft test g" + " lineNo=" + cur.get("Line"), cur.get("g"), result);
            
            BigNumber.shiftRight(a, 1);
            result = a.toString16(OutputFormat.allHex);
            assertEquals("ShiftLeft test h" + " lineNo=" + cur.get("Line"), cur.get("h"), result);
        }
    }

}
