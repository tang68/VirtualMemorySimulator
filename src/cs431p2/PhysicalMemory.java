package cs431p2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class PhysicalMemory {

	private static String[][] RAM = new String[16][256];
	private static final String pagesFilePath = "Project2_test_and_page_files/page_files/";
	private static int frameNumber = 0;

	public static String getValue(int frame, String offset) {
		
		return RAM[frame][getOffSetInDecimal(offset)];
	}

	public static String addNewFrame(String address) {
		
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
		return frameNumber == 15;
	}
	
	private static int getOffSetInDecimal(String offset) {
		

		return Integer.decode("0x" + offset);
		
	}
	
	public static int getNextAvailableFrameNumber() {
		
		return 0;
	}
	
	public static int getFrameNumber() {
		return frameNumber;
	}

//	public static String[][] getRAM() {
//		return RAM;
//	}
//
//	public static void setRAM(String[][] rAM) {
//		RAM = rAM;
//	}



	public static void setFrameNumber(int frameNumber) {
		PhysicalMemory.frameNumber = frameNumber;
	}

}
