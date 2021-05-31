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
        int numberOfFiles = Math.min(listOfFiles.length, 30);
        for (int i = 0; i < numberOfFiles; i++) {
            if (listOfFiles[i].isFile()) {
                filePaths.add(folder+ File.separator+listOfFiles[i].getName());
            }
        }
        return filePaths;
    }

    public static Boolean checkReturnFileName(String fileName){
        String[] temp = fileName.split(File.separator);
        return temp[temp.length-1].startsWith("ReturnLogisticsServiceability");
    }


}
