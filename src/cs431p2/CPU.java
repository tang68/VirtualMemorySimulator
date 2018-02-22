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
		MMU MMUnit = new MMU();
		
		try {
			scanFiles = new Scanner(new FileInputStream(testFilePath));

			/** THE MEAT */
			while (scanFiles.hasNextLine()) {
				
				//read data
				String[] memoryAccess = readData(scanFiles);
				
				//give data to MMU
				MMUnit.processMemoryAccess(memoryAccess);
				
			}

		} catch (FileNotFoundException ex) {
			System.out.println("***** Unable to open file");
		} finally {
			scanFiles.close();
			scanInput.close();
		}
		

	}
	
	private static String[] readData(Scanner scanFiles ) {
		
		String[] memoryAccess = new String[3]; //each access will have maximum of 3 entries 
		
		//read data
		memoryAccess[0] = scanFiles.nextLine();
		memoryAccess[1] = scanFiles.nextLine();
		if (memoryAccess[0] == "1")
			memoryAccess[2] = scanFiles.nextLine();
		
		return memoryAccess;
	}

}
