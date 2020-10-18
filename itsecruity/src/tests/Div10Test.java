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

public class Div10Test extends Lib4Tests
{
    final private String FILENAME = "Div10-Tests.txt";

    private GetTests tests = null;

    public Div10Test()
    {
        
    }
    
    @Test
    public void testDivBy10() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next(); if(continue1(cur)) {continue;}
            BigNumber a = new BigNumber(Short.valueOf(cur.get("s")),cur.get("a"));
            BigNumber b = new BigNumber(Short.valueOf(cur.get("s")),cur.get("b"));
            BigNumber c = new BigNumber(Short.valueOf(cur.get("s")),cur.get("c"));
            BigNumber d = new BigNumber(Short.valueOf(cur.get("s")),cur.get("d"));
            BigNumber e = new BigNumber(Short.valueOf(cur.get("s")),cur.get("e"));
            BigNumber f = new BigNumber(Short.valueOf(cur.get("s")),cur.get("f"));
            BigNumber g = new BigNumber(Short.valueOf(cur.get("s")),cur.get("g"));
            BigNumber h = new BigNumber(Short.valueOf(cur.get("s")),cur.get("h"));
            
            
            BigNumber r;
            BigNumber divisionResult = new BigNumber(Short.valueOf(cur.get("s")), "0");
            BigNumber divisionResult2 = new BigNumber(Short.valueOf(cur.get("s")), "0");
            BigNumber divisionResult3 = new BigNumber(Short.valueOf(cur.get("s")), "0");
            BigNumber divisionResult4 = new BigNumber(Short.valueOf(cur.get("s")), "0");
            BigNumber divisionResult5 = new BigNumber(Short.valueOf(cur.get("s")), "0");
            BigNumber divisionResult6 = new BigNumber(Short.valueOf(cur.get("s")), "0");
            BigNumber divisionResult7 = new BigNumber(Short.valueOf(cur.get("s")), "0");

            
            r = BigNumber.div10(divisionResult, a); // a:= 10/a
            String result = divisionResult.toString16(OutputFormat.allHex);
            assertEquals("division by 10 test" + " lineNo=" + cur.get("Line"), cur.get("b"), result);
            
            r = BigNumber.div10(divisionResult2, divisionResult); // a:= 10/a
            
            result = divisionResult2.toString16(OutputFormat.allHex);
            assertEquals("division by 10 test" + " lineNo=" + cur.get("Line"), cur.get("c"), result);

            r = BigNumber.div10(divisionResult3, divisionResult2); // a:= 10/a
            result = divisionResult3.toString16(OutputFormat.allHex);
            assertEquals("division by 10 test" + " lineNo=" + cur.get("Line"), cur.get("d"), result);

            r = BigNumber.div10(divisionResult4, divisionResult3); // a:= 10/a
            result = divisionResult4.toString16(OutputFormat.allHex);
            assertEquals("division by 10 test" + " lineNo=" + cur.get("Line"), cur.get("e"), result);

            r = BigNumber.div10(divisionResult5, divisionResult4); // a:= 10/a
            result = divisionResult5.toString16(OutputFormat.allHex);
            assertEquals("division by 10 test" + " lineNo=" + cur.get("Line"), cur.get("f"), result);

            r = BigNumber.div10(divisionResult6, divisionResult5); // a:= 10/a
            result = divisionResult6.toString16(OutputFormat.allHex);
            assertEquals("division by 10 test" + " lineNo=" + cur.get("Line"), cur.get("g"), result);

            r = BigNumber.div10(divisionResult7, divisionResult6); // a:= 10/a
            result = divisionResult7.toString16(OutputFormat.allHex);
            assertEquals("division by 10 test" + " lineNo=" + cur.get("Line"), cur.get("h"), result);
            
            
            divisionResult = new BigNumber(Short.valueOf(cur.get("s")), "0");
            divisionResult2 = new BigNumber(Short.valueOf(cur.get("s")), "0");
            divisionResult3 = new BigNumber(Short.valueOf(cur.get("s")), "0");
            divisionResult4 = new BigNumber(Short.valueOf(cur.get("s")), "0");
            divisionResult5 = new BigNumber(Short.valueOf(cur.get("s")), "0");
            divisionResult6 = new BigNumber(Short.valueOf(cur.get("s")), "0");
            divisionResult7 = new BigNumber(Short.valueOf(cur.get("s")), "0");
            
        }
        tests= null;
    }
    
}