/*
 * Copyright (C) 2018 Burkhard Messer <burkhard.messer@htw-berlin.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package testDriver;

import java.util.Arrays;

/**
 *
 * @author Burkhard Messer
 */
@SuppressWarnings("checkstyle:javadocvariable")
public class DataString implements IDataSource {

    private char[] myBag = null;
    private int index = 0;
    private char[] line = null;

    /**
     * to be filled out.
     *
     * @param lines - the content
     */
    public DataString(final String lines) {
        setContent(lines);
    }
    public DataString() {
    }


    /**
     * to be filled out.
     *
     * @param lines - the content
     */
    @Override
    public final void setContent(final String lines) {
        if (lines == null) {
            throw new DataIOException("String is null");
        }
        index = 0;
        myBag = lines.toCharArray();
        if (myBag.length == 0) {
            myBag = null;
        } else {
            readNextLine();
        }
    }
    
    @Override
    public final String getContent() {
        if((myBag!=null)&&(myBag.length>0)) {
            return String.valueOf(myBag);
        } 
        return ""; 
    }
    
    @Override
    public final boolean isFile() {
        return false;
    }

    /**
     * to be filled out.
     *
     * @return boolean
     */
    private boolean isEOLN() {
        return((myBag[index] == '\r')||(myBag[index] == '\n'));
    }

    /**
     * to be filled out.
     *
     */
    private void skipEOLN() {
        if (isEOLN()) {
            char last= myBag[index];
            index++;
            if ((index < myBag.length)&&isEOLN()&&(last!=myBag[index])) {
                index++;
            }
        }
    }

    /**
     * to be filled out.
     *
     */
    private void readNextLine() {
        if (index >= myBag.length) {
            myBag = null;
        } else {
            int startIndex = index;
            while ((index < myBag.length) && !isEOLN()) {
                index++;
            }
            int endIndex = index;
            if (index < myBag.length) {
                skipEOLN();
            }
            line = Arrays.copyOfRange(myBag, startIndex, endIndex);
        }
    }

    /**
     * to be filled out.
     *
     * @return char[]
     */
    @Override
    public char[] readLine() {
        if (myBag == null) {
            throw new DataIOException("Try to read behind EOI");
        }
        char[] chars = line.clone();
        readNextLine();
        return chars;
    }
    @Override
    public String readLineAsString() {
        if (myBag == null) {
            throw new DataIOException("Try to read behind EOI");
        }
        String actLine = String.valueOf(line); 
        readNextLine();
        return actLine;
    }

    /**
     * to be filled out.
     *
     * @return boolean
     */
    @Override
    public boolean eoi() {
        return (myBag == null);
    }
}
