package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;

/**
 *
 * @author Burkhard Messer burkhard.messer@htw-berlin.de
 */
@SuppressWarnings({"checkstyle:javadocvariable", "checkstyle:javadocmethod"})
public class Lib4Tests {

//    private static String path= null;
    protected final static String EOLN = System.getProperty("line.separator");
    protected final static String PACKAGENAME= "tests";
    protected final static String DIRECTORY= "Files";

    
    
//    protected boolean between(int i, int minInclusive, int maxInclusive) {
//        return (i >= minInclusive && i <= maxInclusive);
//    }

//    private String correctData(String value, String base) {
//        // empty string is allowed
//        if(value.length()==0) {
//            return value;
//        }
//        char sign = '+';
//        int startindex = 0;
//        if (value.charAt(0) == '+') {
//            startindex = 1;
//        }
//        if (value.charAt(0) == '-') {
//            sign = '-';
//            startindex = 1;
//        }
//        if (value.substring(startindex, startindex + 1).equals(base)) {
//            startindex = startindex + 2;
//        }
//        if(base.equals("0x")) {
//            int len= value.substring(startindex).length();
//            if((len&1)==1) {
//                throw new RuntimeException("wrong test data: size hex value odd");
//            }
//        }
//        return sign + base + value.substring(startindex);
//    }
//
//    protected String correctHex(String value) {
//        return correctData(value, "0x");
//    }
//
//    protected String correctOctal(String value) {
//        return correctData(value, "0o");
//    }
    
    
    protected boolean continue1 (HashMap<String, String> cur)
    {
    	if(cur.get("t").equals("Div10-8"))
    	{
    		return true;
    	}
    	return false;
    }
    
    protected String reduceHexString(String hexString) {
        // +0x00000000yyyy to +0xyyyy
        int i;
        int len= hexString.length()-1;  // minimum is 1 char
        char sign= hexString.charAt(0);
        for(i=3; i<len;i++) {           // without +0x
            if(hexString.charAt(i)!='0') {
                break;
            }
        }
        return hexString.substring(0, 3)+hexString.substring(i);
    }
    
    protected boolean onlyTestCase(HashMap<String, String> cur,String name) {
        return cur.get("t").equals(name);
    }
}
