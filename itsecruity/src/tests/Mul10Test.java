package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import static tests.Lib4Tests.PACKAGENAME;
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
public class Mul10Test extends Lib4Tests
{
    final private String FILENAME = "Mul10-Tests.txt";

    private GetTests tests = null;

    public Mul10Test()
    {
        
    }
    
    @Test
    public void testMultiplicationBy10() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();            
            
            BigNumber a = new BigNumber(Short.valueOf(cur.get("s")),cur.get("a"));
            BigNumber b = new BigNumber(Short.valueOf(cur.get("s")),cur.get("b"));
            BigNumber c = new BigNumber(Short.valueOf(cur.get("s")),cur.get("c"));
            BigNumber d = new BigNumber(Short.valueOf(cur.get("s")),cur.get("d"));
            BigNumber e = new BigNumber(Short.valueOf(cur.get("s")),cur.get("e"));
            BigNumber f = new BigNumber(Short.valueOf(cur.get("s")),cur.get("f"));
            BigNumber g = new BigNumber(Short.valueOf(cur.get("s")),cur.get("g"));
            BigNumber h = new BigNumber(Short.valueOf(cur.get("s")),cur.get("h"));
            
            BigNumber r;
            r= BigNumber.mul10(a); // a:= 10*a
            String result = r.toString16(OutputFormat.allHex);
            assertEquals("multiplication by 10 test" + " lineNo=" + cur.get("Line"), cur.get("b"), result);
            
            r= BigNumber.mul10(r); // a:= 10*a
            result = r.toString16(OutputFormat.allHex);
            assertEquals("multiplication by 10 test" + " lineNo=" + cur.get("Line"), cur.get("c"), result);
            
            r= BigNumber.mul10(r); // a:= 10*a
            result = r.toString16(OutputFormat.allHex);
            assertEquals("multiplication by 10 test" + " lineNo=" + cur.get("Line"), cur.get("d"), result);
            
            r= BigNumber.mul10(r); // a:= 10*a
            result = r.toString16(OutputFormat.allHex);
            assertEquals("multiplication by 10 test" + " lineNo=" + cur.get("Line"), cur.get("e"), result);
            
            r= BigNumber.mul10(r); // a:= 10*a
            result = r.toString16(OutputFormat.allHex);
            assertEquals("multiplication by 10 test" + " lineNo=" + cur.get("Line"), cur.get("f"), result);
            
            r= BigNumber.mul10(r); // a:= 10*a
            result = r.toString16(OutputFormat.allHex);
            assertEquals("multiplication by 10 test" + " lineNo=" + cur.get("Line"), cur.get("g"), result);
            
            r= BigNumber.mul10(r); // a:= 10*a
            result = r.toString16(OutputFormat.allHex);
            assertEquals("multiplication by 10 test" + " lineNo=" + cur.get("Line"), cur.get("h"), result);
        }
        tests= null;
    }
    
}