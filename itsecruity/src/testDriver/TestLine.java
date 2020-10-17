package testDriver;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */
public class TestLine {
    // abc = data EOLN
    public String name;
    public String data;
    public int lineNo;
    
    TestLine(String name,String data, int lineNo) {
        this.name= name;
        this.data= data;
        this.lineNo= lineNo;
    }
    
    
    TestLine(String name,String data) {
        this.name= name;
        this.data= data;
        this.lineNo= 0;
    }
}
