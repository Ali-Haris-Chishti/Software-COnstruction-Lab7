import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

public class FileSearcherTest {

    private static final String TEST_ROOT_DIR = "testDir";
    private static final String EXISTING_FILE_NAME = "existingFile.txt";
    private static final String NON_EXISTING_FILE_NAME = "nonExistingFile.txt";
    private static final String NESTED_DIR = "nestedDir";

    @Before
    public void setUp() throws IOException {
        // Set up test directories and files
        File rootDir = new File(TEST_ROOT_DIR);
        rootDir.mkdir();

        File nestedDir = new File(rootDir, NESTED_DIR);
        nestedDir.mkdir();

        // Create a file in the root directory
        new File(rootDir, EXISTING_FILE_NAME).createNewFile();

        // Create a file in the nested directory
        new File(nestedDir, EXISTING_FILE_NAME).createNewFile();
    }

    @Test(expected = RuntimeException.class)
    public void testDirectoryDoesNotExist() {
        // Test for a non-existent directory
        FileSearcher.checkIfDirectoryExists("nonExistentDir");
    }

    @Test(expected = RuntimeException.class)
    public void testPathIsNotDirectory() throws IOException {
        // Create a file to test the case where path is not a directory
        File tempFile = new File(TEST_ROOT_DIR + "/tempFile.txt");
        tempFile.createNewFile();

        // Test with the path to a file instead of a directory
        FileSearcher.checkIfDirectoryExists(tempFile.getAbsolutePath());
    }

    @Test
    public void testFileFoundInRootDirectory() {
        // Search for a file that exists in the root directory
        String foundFilePath = FileSearcher.getSearchedFilePath(TEST_ROOT_DIR, EXISTING_FILE_NAME);
        assertTrue("File should be found in the root directory", new File(foundFilePath).isFile());
    }

    @Test
    public void testFileFoundInNestedDirectory() {
        // Search for a file that exists in a nested directory
        String foundFilePath = FileSearcher.getSearchedFilePath(TEST_ROOT_DIR, EXISTING_FILE_NAME);
        assertTrue("File should be found in a nested directory", new File(foundFilePath).isFile());
    }

    @Test
    public void testFileNotFound() {
        // Search for a file that does not exist
        String foundFilePath = FileSearcher.getSearchedFilePath(TEST_ROOT_DIR, NON_EXISTING_FILE_NAME);
        assertFalse("File should not be found", new File(foundFilePath).isFile());
    }

    @Test
    public void testRootDirectoryReturnedWhenFileNotFound() {
        // Check if the root directory path is returned when the file is not found
        String foundFilePath = FileSearcher.getSearchedFilePath(TEST_ROOT_DIR, NON_EXISTING_FILE_NAME);
        assertEquals("Root directory path should be returned if file is not found",
                new File(TEST_ROOT_DIR).getAbsolutePath(), foundFilePath);
    }
}
