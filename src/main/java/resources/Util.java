package resources;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class Util {
    public ArrayList<String> getFilesToUpload() {
        File folder = new File(System.getProperty("folderPath"));
        FileFilter fileFilter = new WildcardFileFilter("*.csv", IOCase.INSENSITIVE);
        File[] listOfFiles = folder.listFiles(fileFilter);
        ArrayList<String> filePaths = new ArrayList<>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                filePaths.add(folder+ File.separator+listOfFiles[i].getName());
            }
        }
        return filePaths;
    }


}
