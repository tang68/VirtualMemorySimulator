package cs431p2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhysicalMemory {

	private static String[][] RAM = new String[16][256];
	private static final String pagesFilePath = "Project2_test_and_page_files/page_files/";
	private static int frameNumber = 0;

	public static String getValue(int frame, String offset) {
		
		return RAM[frame][getOffSetInDecimal(offset)];
	}

	public static String writeToMemory(int frame, String offset, String value) {
		
		RAM[frame][getOffSetInDecimal(offset)] = value;
		
		return value;
	}
	
	public static String addNewFrame(String[] memoryAccess) {
		
		String address = memoryAccess[1];
		String returnVal = "";
		String virtualPageNumber = address.substring(0, 2);
		String offset = address.substring(2, 4);
		Scanner scanFiles = null;
		String testFilePath = pagesFilePath + virtualPageNumber + ".pg";
		try {
			scanFiles = new Scanner(new FileInputStream(testFilePath));
			
			int offsetIndex = 0;
			while (scanFiles.hasNextLine()) {
				String num = scanFiles.nextLine();
				if (num.length() > 0) {
					RAM[frameNumber][offsetIndex] = num;
					offsetIndex++;
				}
			}
			
			if (memoryAccess[0].equals("1"))
				RAM[frameNumber][getOffSetInDecimal(offset)] = memoryAccess[2];
			
			returnVal = RAM[frameNumber][getOffSetInDecimal(offset)];
			frameNumber++;

		} catch (FileNotFoundException ex) {
			System.out.println("***** Unable to open file");
		} finally {
			scanFiles.close();
		}
		
		return returnVal;
		
	}
	
	public static Boolean isMemoryFull() {
		
		for (int i = 0; i < RAM.length; i++) {
			if (RAM[i][0] == null)
				return false;
		}
		return true;
	}
	
	private static int getOffSetInDecimal(String offset) {
		return Integer.decode("0x" + offset);
	}
	
	//return max int if RAM is full, else loop thru, return i where RAM[i][0] that is not null
	public static int getNextAvailableFrame() {
		return 0;
	}
	
	public static String[][] getRAM() {
		return RAM;
	}

	public static void setRAM(String[][] rAM) {
		RAM = rAM;
	}

	public static int getFrameNumber() {
		return frameNumber;
	}

	public static void setFrameNumber(int frameNumber) {
		PhysicalMemory.frameNumber = frameNumber;
	}

}
