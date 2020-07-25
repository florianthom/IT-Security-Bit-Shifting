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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * to be filled out.
 * @author Burkhard Messer
 */
@SuppressWarnings("checkstyle:javadocvariable")
public class DataFile implements IDataSource {

    private String line = null;
    private FileInputStream fileIS = null;
    private InputStreamReader fileISReader = null;
    private BufferedReader file = null;
    private String fileName= null;

    
    
    
    /**
     * shortcut for DataFile() and setContent(..).
     *
     * @param fileNameParam - the file name
     */
    public DataFile(final String fileNameParam) {
        setContent(fileNameParam);
    }

    
    
    
    /**
     * to be filled out.
     *
     * @param fileNameParam - the file name
     */
    private void openFile(final String fileNameParam) {
    	System.out.println(fileNameParam);
        try {
            fileIS = new FileInputStream(fileNameParam);
            fileISReader = new InputStreamReader(fileIS,
                                                 StandardCharsets.ISO_8859_1);
            file = new BufferedReader(fileISReader);
        } catch (FileNotFoundException ex) {
            throw new DataIOException(ex.getMessage());
        }
        readNextLine();
    }

    
    
    
    
    /**
     * to be filled out.
     *
     */
    private void closeFile() {
        try {
            if (file != null) {
                file.close();
                file = null;
                fileISReader = null;
                fileIS = null;
                line = null;
            }
        } catch (IOException ex) {
        }
    }

    
    
    
    
    /**
     * sets fileName variable and reads first line into line attribute (-> setContent="setFilename")
     *
     * @param fileNameParam - the file name
     */
    @Override
    public final void setContent(final String fileNameParam) {
        if ((fileNameParam == null) || (fileNameParam.isEmpty())) {
            throw new DataIOException("File name is empty");
        }
        fileName= fileNameParam;
        closeFile();
        openFile(fileNameParam);
    }
    
    
    
    
    @Override
    public final String getContent() {
        return this.fileName;
    }
    
    
    
    
    @Override
    public final boolean isFile() {
        return true;
    }

    
    
    
    /**
     * to be filled out.
     *
     */
    public final void close() {
        closeFile();
    }

    
    
    /**
     * to be filled out.
     *
     */
    private void readNextLine() {
        line = null;
        try {
            line = file.readLine();
        } catch (IOException ex) {
            throw new DataIOException(ex.getMessage());
        }
    }

    
    
    /**
     * returns current line and reads next line into line - attribute
     *
     * @return char[]
     */
    @Override
    public char[] readLine() {
        if (line == null) {
            throw new DataIOException("Try to read behind EOI");
        }
        char[] chars = line.toCharArray();
        readNextLine();
        return chars;
    }
    
    
    
    /**
     * returns current value in line - attribute and reads next line
     */
    @Override
    public String readLineAsString() {
        if (line == null) {
            throw new DataIOException("Try to read behind EOI");
        }
        String actLine = line;  // string "copy"
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
        return (line == null);
    }
}
