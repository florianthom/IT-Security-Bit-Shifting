/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDriver;

/**
 * @author Burkhard Messer <burkhard.messer@htw-berlin.de>
 */
import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

@SuppressWarnings({"checkstyle:javadocvariable", "checkstyle:javadocmethod"})
public class FileNameInPackage {

    private String path = null;
    private String file= null;
    private boolean testMode = false;

    public FileNameInPackage() {
        String packageName = this.getClass().getPackage().getName();
        doSetFilePath(packageName, "Files");

    }

    public FileNameInPackage(String packageNameParam) {
        doSetFilePath(packageNameParam, "Files");

    }

    public FileNameInPackage(String packageNameParam, String dir) {
        doSetFilePath(packageNameParam, dir);
    }

    public FileNameInPackage(boolean mode) {
        testMode = mode;
        String packageName = this.getClass().getPackage().getName();
        doSetFilePath(packageName, "Files");

    }

    public FileNameInPackage(boolean mode, String packageNameParam) {
        testMode = mode;
        doSetFilePath(packageNameParam, "Files");

    }

    public FileNameInPackage(boolean mode, String packageNameParam, String dir) {
        testMode = mode;
        doSetFilePath(packageNameParam, dir);
    }

    private void doSetFilePath(String packageNameParam, String dir) {
        String packageName;
        FileSystem fileSystem = FileSystems.getDefault();
        String delim = fileSystem.getSeparator();
        packageNameParam = packageNameParam + ".";
        if (dir != null) {
            packageNameParam = packageNameParam + dir + ".";
        }
        
        
        
        
        
//        if (testMode) {
//            packageName = "src.test.java." + packageNameParam;
//        } else {
//            packageName = "src.main.java." + packageNameParam;
//        }
        
        packageName="src." + packageNameParam;
        
        
        
        
        
        
        path = packageName.replace(".", delim);
        File file = new File(path);
        System.out.println(file.getAbsolutePath());
        if (!file.exists()) {
            throw new DataIOException("directory not found: " + packageNameParam);
        }
    }

    public String getFileName(final String lfn) {
    	String tmp = path + lfn;
        return tmp;
    }

    public String getFileName() {
        return path+file;
    }
    public void setFileName(final String lfn) {
        file= lfn;
    }

    public boolean cmpFileName(final String lfn, final String correct) {
        return correct.equals(path + lfn);
    }

    public void setFilePath(String packageNameParam) {
        doSetFilePath(packageNameParam, null);
    }

    public void setFilePath(String packageNameParam, String dir) {
        doSetFilePath(packageNameParam, dir);
    }
}
