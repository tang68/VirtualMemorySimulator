package cs431p2;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class CPU {

	public static void main(String[] args) {
		
		Scanner scanInput = new Scanner(System.in);
		System.out.println("Enter test file name: ");
//		String testFile = scanInput.next();
//		String testFilePath = "Project2_test_and_page_files/test_files/" + testFile;
		String testFilePath = "Project2_test_and_page_files/test_files/test_1.txt";
		
		Scanner scanFiles = null;
		try {
			scanFiles = new Scanner(new FileInputStream(testFilePath));

			/** THE MEAT */
			while (scanFiles.hasNextLine()) {
				
			}

		} catch (FileNotFoundException ex) {
			System.out.println("***** Unable to open file");
		} finally {
			scanFiles.close();
			scanInput.close();
		}
		

	}

}
