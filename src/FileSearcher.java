import java.io.File;

public class FileSearcher {

    public static File checkIfDirectoryExists(String path) throws RuntimeException {
        File file = new File(path);
        if (!file.exists())
            throw new RuntimeException("Directory With Given Path Does Not Exists");
        if (!file.isDirectory())
            throw new RuntimeException("Given path is not of a directory but a file");
        return file;
    }

    public static String getSearchedFilePath(String directoryPath, String fileName) throws RuntimeException {
        File rootDirectory = new File(directoryPath);


        File [] childrenFiles = rootDirectory.listFiles();
        for (File child : childrenFiles) {
            if (child.isFile() && child.getName().equals(fileName)) // returns absolute path if found
                return child.getAbsolutePath();
        }
        for (File child : childrenFiles) {
            if (child.isDirectory()) {
                String childPath = getSearchedFilePath(child.getAbsolutePath(), fileName);
                if (new File(childPath).isFile()) // file path is returned only when name matches filename, so if it is a file, it is definitely the required file
                    return childPath;
            }
        }
        // when the recursion ends by this statement, instead of a file, a directory is returned, we can check that to know if the file was found or not
        return rootDirectory.getAbsolutePath();
    }
}
