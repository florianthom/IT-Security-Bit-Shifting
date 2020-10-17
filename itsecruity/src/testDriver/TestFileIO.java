package testDriver;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */

public class TestFileIO implements Iterator {
    private IDataSource ds = null;
    char[] chars= null;     // line
    private int col= 0;     // column
    private int max= 0;     // max index in chars
    private int lineNo= 0;  // number of line
    
    TestFileIO(IDataSource dsParam)  {
        ds = dsParam;
        skipComments();
    }
    
    
    /**
     * use: deletes leading blank space (to the actual data oder #-comment)
     */
    private void skipBlanks() {
        if(col<=max) {
            while(chars[col]==' ') {
                col++;
                if(col>max) {
                    return;
                }
            }
        }
    }
    
    
    /**
     * sets lineNo (lineNumber) to the lineNumber after the comment
     */
    private void skipComments() {
        boolean comment= true;
        do {
            if(ds.eoi()) {
                return;    
            }
            
            chars = ds.readLine();
            
            
            lineNo++;
            col= 0;
            max= chars.length-1;
            skipBlanks();
            if(col<=max) {
                comment= (chars[col]=='#');
            }
        } while (comment);
    }
    
    
    /**
     * reads name of the variable example line: a=+0x0000000000000000... to a
     * and sets col to the position right after "="
     * @return
     */
    private String collectName() {
        int startcol= col;
        while(chars[col]!='=') {
            col++;
            if(col>max){
                throw new SyntaxErrorException("syntax error at line "+lineNo);
            }
        }
        String name= new String(chars,startcol,col-startcol);
        col++; // skip '='
        return name.trim();
    }
    
    
    /**
     * reads value of the current variable example line: a=+0x0000000000000000... to 0x0000000000000000...
     * -> what is current variable? -> current variable is the variable in the line lineNo and the value is taken from col - attribute
     * and sets col to the position right after "="
     * @return
     */
    private String collectData() {
        // a=EOLN is allowed
        if(col>max) {
            return "";  // empty string
//            throw new SyntaxErrorException("syntax error at line "+lineNo);
        }
        String data= new String(chars,col,max-col+1);
        return data.trim();
    }
    
    @Override
    public boolean hasNext() {
        return !ds.eoi();
    }

    
    /**
     * reads+returns current line as TestLine-Class-Format(this is no comment because constructor calls skipComments)
     * and calls skipComments again
     */
    @Override
    public TestLine next() {
        if(ds.eoi()) {
            throw new NoSuchElementException();
        }
        TestLine result = new TestLine(collectName(),collectData(),lineNo);
        skipComments();
        return result;
    }
    
    
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
