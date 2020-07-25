package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import static tests.Lib4Tests.PACKAGENAME;
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
public class Mul10Test extends Lib4Tests {
    final private String FILENAME = "Mul10-Tests.txt";

    private GetTests tests = null;

    public Mul10Test() {
        
    }
    
    @Test
    public void testMultiplicationBy10() {
        tests = new GetTests(PACKAGENAME, DIRECTORY, FILENAME);
        Iterator<HashMap<String, String>> ti = tests.iterator();
        while (ti.hasNext()) {
            HashMap<String, String> cur = ti.next();
//            if(!onlyTestCase(cur,"Mul10-6")) {
//                continue;
//            }
            
//            System.out.println("####### test "+cur.get("t")+" multiplication by 10");
//            System.out.println(cur);
            BigInt a = new BigInt(Short.valueOf(cur.get("s")),cur.get("a"));
            BigInt b = new BigInt(Short.valueOf(cur.get("s")),cur.get("b"));
            BigInt c = new BigInt(Short.valueOf(cur.get("s")),cur.get("c"));
            BigInt d = new BigInt(Short.valueOf(cur.get("s")),cur.get("d"));
            BigInt e = new BigInt(Short.valueOf(cur.get("s")),cur.get("e"));
            BigInt f = new BigInt(Short.valueOf(cur.get("s")),cur.get("f"));
            BigInt g = new BigInt(Short.valueOf(cur.get("s")),cur.get("g"));
            BigInt h = new BigInt(Short.valueOf(cur.get("s")),cur.get("h"));
            
            BigInt r;
            r= BigInt.mul10(a); // a:= 10*a
            String result = r.toString16(OutputFormat.allHex);
            assertEquals(cur.get("b"), result, "multiplication by 10 test" + " lineNo=" + cur.get("Line"));
//            System.out.println("         test "+cur.get("t")+" multiplication by 10 c");
            r= BigInt.mul10(r); // a:= 10*a
            result = r.toString16(OutputFormat.allHex);
            assertEquals(cur.get("c"), result, "multiplication by 10 test" + " lineNo=" + cur.get("Line"));
//            System.out.println("         test "+cur.get("t")+" multiplication by 10 d");
            r= BigInt.mul10(r); // a:= 10*a
            result = r.toString16(OutputFormat.allHex);
            assertEquals(cur.get("d"), result, "multiplication by 10 test" + " lineNo=" + cur.get("Line"));
//            System.out.println("         test "+cur.get("t")+" multiplication by 10 e");
            r= BigInt.mul10(r); // a:= 10*a
            result = r.toString16(OutputFormat.allHex);
            assertEquals(cur.get("e"), result, "multiplication by 10 test" + " lineNo=" + cur.get("Line"));
//            System.out.println("         test "+cur.get("t")+" multiplication by 10 f");
            r= BigInt.mul10(r); // a:= 10*a
            result = r.toString16(OutputFormat.allHex);
            assertEquals(cur.get("f"), result, "multiplication by 10 test" + " lineNo=" + cur.get("Line"));
//            System.out.println("         test "+cur.get("t")+" multiplication by 10 g");
            r= BigInt.mul10(r); // a:= 10*a
            result = r.toString16(OutputFormat.allHex);
            assertEquals(cur.get("g"), result, "multiplication by 10 test" + " lineNo=" + cur.get("Line"));
//            System.out.println("         test "+cur.get("t")+" multiplication by 10 h");
            r= BigInt.mul10(r); // a:= 10*a
            result = r.toString16(OutputFormat.allHex);
            assertEquals(cur.get("h"), result, "multiplication by 10 test" + " lineNo=" + cur.get("Line"));
            
        }
        tests= null;
    }
    
}