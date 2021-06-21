package resources;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.regex.Pattern;

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

    public static Boolean checkReturnFileName(String fileName){
        String pattern = Pattern.quote(System.getProperty("file.separator"));
        String[] temp = fileName.split(pattern);
        return temp[temp.length-1].startsWith("ReturnLogisticsServiceability");
    }


}
