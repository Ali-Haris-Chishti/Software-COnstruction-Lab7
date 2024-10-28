import java.io.File;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.print("Enter root Directory Path: ");
        String rootPath = scanner.nextLine();

        System.out.print("Enter file name to be searched: ");
        String fileName = scanner.nextLine();

        FileSearcher.checkIfDirectoryExists(rootPath);

        String absolutePath = FileSearcher.getSearchedFilePath(rootPath, fileName);

        if (new File(absolutePath).isFile())
            System.out.println("Absolute Path: " + absolutePath);
        else
            System.out.println("File: " + fileName + " not present in any sub directories of ");
    }
}