package sample;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DirectorySearcher implements Runnable {
    private String directoryName;
    private List<File> thisResult = new ArrayList<File>();
    private Map<String, List<File>> allResults;

    public DirectorySearcher(String directoryName, Map<String, List<File>> allResults) {
        this.directoryName = directoryName;
        this.allResults = allResults;
    }


    public void run() {
        searchDirectory(new File(directoryName));
        allResults.put(directoryName, thisResult);
    }

    private void searchDirectory(File directory) {
        for (File fileEntry : directory.listFiles()) {
            thisResult.add(fileEntry);

            if (fileEntry.isDirectory()) {
                searchDirectory(fileEntry);
            }
        }
    }
}
