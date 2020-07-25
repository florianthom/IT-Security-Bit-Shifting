package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
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
public class ShiftLeftTest extends Lib4Tests {

    private final static String FILENAME = "Shift-Left-Tests.txt";

    private static GetTests tests = null;

    public ShiftLeftTest() {
        
    }
    
    @Before
    public static void setUp() {
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
//            if(!onlyTestCase(cur,"ShiftLeft-7")) {
//                continue;
//            }
//            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//            System.out.println(cur);
            BigInt a = new BigInt(Short.valueOf(cur.get("s")),cur.get("a"));
            BigInt.shiftLeft(a, 1);
            result = a.toString16(OutputFormat.allHex);
            assertEquals(cur.get("b"), result, "ShiftLeft test b" + " lineNo=" + cur.get("Line"));
            BigInt.shiftLeft(a, 1);
            result = a.toString16(OutputFormat.allHex);
            assertEquals(cur.get("c"), result, "ShiftLeft test c" + " lineNo=" + cur.get("Line"));
            BigInt.shiftLeft(a, 1);
            result = a.toString16(OutputFormat.allHex);
            assertEquals(cur.get("d"), result, "ShiftLeft test d" + " lineNo=" + cur.get("Line"));
            BigInt.shiftLeft(a, 1);
            result = a.toString16(OutputFormat.allHex);
            assertEquals(cur.get("e"), result, "ShiftLeft test e" + " lineNo=" + cur.get("Line"));
            BigInt.shiftLeft(a, 1);
            result = a.toString16(OutputFormat.allHex);
            assertEquals(cur.get("f"), result, "ShiftLeft test f" + " lineNo=" + cur.get("Line"));
            BigInt.shiftLeft(a, 1);
            result = a.toString16(OutputFormat.allHex);
            assertEquals(cur.get("g"), result, "ShiftLeft test g" + " lineNo=" + cur.get("Line"));
            BigInt.shiftLeft(a, 1);
            result = a.toString16(OutputFormat.allHex);
            assertEquals(cur.get("h"), result, "ShiftLeft test h" + " lineNo=" + cur.get("Line"));
        }
    }

}
