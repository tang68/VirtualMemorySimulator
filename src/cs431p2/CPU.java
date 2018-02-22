package cs431p2;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
			String[][] output = new String[1000][8];
			int outputIndex = 0;
			
			/** THE MEAT */
			while (scanFiles.hasNextLine()) {
				
				//read data
				String[] memoryAccess = readData(scanFiles);
				System.out.println(Arrays.toString(memoryAccess));
				//give data to MMU for fetching or writing, store the result 
				// of what happen after that access into an array
				String[] tmp = MMUnit.processMemoryAccess(memoryAccess);
				
				//put tmp array to the output array
				output[outputIndex] = tmp;
				outputIndex++;
				if (outputIndex == output.length)
					output = Arrays.copyOf(output, output.length * 2);
				
			}
			
			outputToCSV(output, "o1.csv");

		} catch (FileNotFoundException ex) {
			System.out.println("***** Unable to open file");
		} finally {
			scanFiles.close();
			scanInput.close();
		}
		

	}
	
	private static void outputToCSV(String[][] result, String fileName)  {
		//result stored in list of 5 lists (CPU time, PID, SburstTime, EburstTime, Completion)
		
		try {
			
			FileWriter writer = new FileWriter(fileName);
			
			CSVUtils.writeLine(writer, Arrays.asList("Address", "R/W", "Value", 
					"Soft", "Hard", "Hit", "EvictedPageNumber", "DirtyEvictedPage"));
			
			for (int i = 0; i < result.length; i++) {
				if (result[i][0] == null)
					break;
				else
					CSVUtils.writeLine(writer, Arrays.asList(result[i]));
			}
			
			
			writer.flush();
			writer.close();
			
		} catch (Exception ex) {
			System.out.println("Unable to write to csv file");
			ex.printStackTrace();
		}
	}
	
	private static String[] readData(Scanner scanFiles ) {
		
		String[] memoryAccess = new String[3]; //each access will have maximum of 3 entries 
		
		//read data
		memoryAccess[0] = scanFiles.nextLine();
		memoryAccess[1] = scanFiles.nextLine();
		if (memoryAccess[0].equals("1"))
			memoryAccess[2] = scanFiles.nextLine();
		
		return memoryAccess;
	}
	

}




















