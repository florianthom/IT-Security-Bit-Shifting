package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import org.junit.runner.RunWith;

import itsecruity.BigNumber;
import itsecruity.enums.OutputFormat;
import testDriver.GetTests;


public class CompareTest extends Lib4Tests {
	
	
    final private String FILENAME = "Compare-Tests.txt";

    private GetTests tests = null;

    public CompareTest()
    {
        // to test lt, le, eq,
    }

    
    @Test
    public void testCompareLessThan()
    {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
//            if(!onlyTestCase(cur,"Compare-HexDec-2")) {
//                continue;
//            }

            BigNumber a = new BigNumber(Short.valueOf(cur.get("s")),cur.get("a"));
            BigNumber A = new BigNumber(Short.valueOf(cur.get("s")),cur.get("A"));
            assertEquals("convert to dec test" + " lineNo=" + cur.get("Line"), a.toString16(), A.toString16());
            
            BigNumber b = new BigNumber(Short.valueOf(cur.get("s")),cur.get("b"));
            BigNumber B = new BigNumber(Short.valueOf(cur.get("s")),cur.get("B"));
            assertEquals("convert to dec test" + " lineNo=" + cur.get("Line"), b.toString16(), B.toString16());

            String result = BigNumber._ltSign(a, b) ? "-1" : (BigNumber._ltSign(b, a) ? "+1" : "+0");
            assertEquals("compare test - lessthan" + " lineNo=" + cur.get("Line"), cur.get("c"), result);
            
            result = BigNumber._ltSign(a, b) ? "+1" : (BigNumber._ltSign(b, a) ? "-1" : "+0");
            assertEquals("compare test - lessthan" + " lineNo=" + cur.get("Line"), cur.get("d"), result);
        }
        tests= null;
    }
    
    @Test
    public void testCompareEquals()
    {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
//            if(!onlyTestCase(cur,"Compare-HexDec-2")) {
//                continue;
//            }

            BigNumber a = new BigNumber(Short.valueOf(cur.get("s")),cur.get("a"));
            BigNumber A = new BigNumber(Short.valueOf(cur.get("s")),cur.get("A"));
            assertEquals("convert to dec test" + " lineNo=" + cur.get("Line"), a.toString16(), A.toString16());
            
            BigNumber b = new BigNumber(Short.valueOf(cur.get("s")),cur.get("b"));
            BigNumber B = new BigNumber(Short.valueOf(cur.get("s")),cur.get("B"));
            assertEquals("convert to dec test" + " lineNo=" + cur.get("Line"), b.toString16(), B.toString16());

            String result;
            result = BigNumber._equals(a, b) ? "+0" : (BigNumber._equals(b, a) ? "+0" : "-1");
            
            String equals0 =  cur.get("c").equals("+0") ? "+0" : "-1";
            String equals1 =  cur.get("d").equals("+0") ? "+0" : "-1";
            
            assertEquals("compare test - equals" + " lineNo=" + cur.get("Line"), equals0, result);
            assertEquals("compare test - equals" + " lineNo=" + cur.get("Line"), equals1, result);
        }
        tests= null;
    }
    
    
    @Test
    public void testCompareLessEquals()
    {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
            if(!onlyTestCase(cur,"Compare-HexDec-2")) {
                continue;
            }

            BigNumber a = new BigNumber(Short.valueOf(cur.get("s")),cur.get("a"));
            BigNumber A = new BigNumber(Short.valueOf(cur.get("s")),cur.get("A"));
            assertEquals("convert to dec test" + " lineNo=" + cur.get("Line"), a.toString16(), A.toString16());
            
            BigNumber b = new BigNumber(Short.valueOf(cur.get("s")),cur.get("b"));
            BigNumber B = new BigNumber(Short.valueOf(cur.get("s")),cur.get("B"));
            assertEquals("convert to dec test" + " lineNo=" + cur.get("Line"), b.toString16(), B.toString16());

            String result;
            result = BigNumber._leSign(a, b) ? "+0" : "-1";
            
            String equals0 = (cur.get("c").equals("+0") || cur.get("c").equals("-1")) ? "+0" : "-1";
            String equals1 = (cur.get("d").equals("+0") || cur.get("c").equals("-1")) ? "+0" : "-1";

            assertEquals("compare test - lessEquals" + " lineNo=" + cur.get("Line"), equals0, result);
            assertEquals("compare test - lessEquals" + " lineNo=" + cur.get("Line"), equals1, result);

        }
        tests= null;
    }
    
}