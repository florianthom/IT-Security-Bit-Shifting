package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import testDriver.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */
public class GetTests implements Iterable<HashMap<String, String>>{

    private TestFileIO tfile= null;
    
    public GetTests(String packageNameParam, String dir, String fileName) {
        FileNameInPackage fn;
        IDataSource df;
        fn= new FileNameInPackage(true, packageNameParam,dir);
        fn.setFileName(fileName);
        df= new DataFile(fn.getFileName());
        tfile= new TestFileIO(df);
    }
    
    
    
    
    @Override
    public Iterator<HashMap<String, String>> iterator() { 
        return new TestIterator(this);
    }
    
    
    
    
    
    
    
    
    
    /**
     * 1 element = 1 Testcase with all variables and their values stored in a hashmap
     * @author Florian
     *
     */
    private class TestIterator implements Iterator<HashMap<String, String>> { 
        final static String DEFAULT_BITSIZE= "256";
        TestLine tl;
        TestLine defaultSize= new TestLine("s", DEFAULT_BITSIZE,0);;
      
        TestIterator(GetTests obj) { 
            initialization();
        } 
        TestIterator() { 
            initialization();
        } 

        private void initialization() {
            if(tfile.hasNext()) {
                tl= tfile.next();  // possible s=number
                if(tl.name.equals("s")) {
                    defaultSize.data= tl.data;
                    if(tfile.hasNext()) {
                        tl= tfile.next();
                    }
                } 
                // skip to title
                while(!tl.name.equals("t")) {
                    if(tfile.hasNext()) {
                        tl= tfile.next();
                    } else {
                        tl= null;
                        break;
                    }
                }
            } else {
                tl= null;
            }
        }
        @Override
        public boolean hasNext() { 
            return tfile.hasNext();
        } 

        
        
        @Override
        public HashMap<String, String> next() { 
            HashMap<String, String> testCase= new HashMap<>();
            testCase.put(defaultSize.name, defaultSize.data);
            if(tl!=null) {
                testCase.put("Line", Integer.toString(tl.lineNo));
//                System.out.println("******************|"+tl.name+"|=|"+tl.data+"|");
                testCase.put(tl.name, tl.data); // title
                while (tfile.hasNext()) {
                    tl= tfile.next();
                    if(tl.name.equals("t")) {
                        return testCase;
                    } else {
                        testCase.put(tl.name, tl.data);
//                        System.out.println("|"+tl.name+"|=|"+tl.data+"|"); 
                    }
                } 
                tl= null;
                return testCase;    // last test
            }
            return null;        // no test
        } 

        @Override
        public void remove() { 
            throw new UnsupportedOperationException();
        } 
    }
} 
