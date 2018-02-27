package cs431p2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhysicalMemory {

	private static RAMEntries[] RAM = new RAMEntries[16];
	private static final String pagesFilePath = "Project2_test_and_page_files/page_files/";
	private static int frameNumber = 0;
	
	public static String addDataToMemory(String[] memoryAccess, int frame) {
		
		String address = memoryAccess[1];
		String returnVal = "";
		String virtualPageNumber = address.substring(0, 2);
		String offset = address.substring(2, 4);
		Scanner scanFiles = null;
		String testFilePath = pagesFilePath + virtualPageNumber + ".pg";
		try {
			scanFiles = new Scanner(new FileInputStream(testFilePath));
			
			int offsetIndex = 0;
			String[] tmpRAMItem = new String[256];
			while (scanFiles.hasNextLine()) {
				String num = scanFiles.nextLine();
				if (num.length() > 0) {
					tmpRAMItem[offsetIndex] = num;
					offsetIndex++;
				}
			}
			
			RAM[frameNumber] = new RAMEntries();
			RAM[frameNumber].setRAMItem(tmpRAMItem);
			RAM[frameNumber].setVirtualPage(virtualPageNumber);
			
			OS.updateClock();
			
			if (memoryAccess[0].equals("1")) {
				RAM[frame].write(getOffSetInDecimal(offset), memoryAccess[2]);
				RAM[frame].setValidBit("1");
				RAM[frame].setRefBit("1");
				RAM[frame].setDirtyBit("1");
			}

			returnVal = RAM[frameNumber].getValueWrittenToMemory(getOffSetInDecimal(offset));
			

		} catch (FileNotFoundException ex) {
			System.out.println("***** Unable to open file");
		} finally {
			scanFiles.close();
		}
		
		//check to see if memory is full
		frameNumber = getNextAvailableFrame();
		if (frameNumber == Integer.MAX_VALUE)
			frameNumber = OS.evict();
		
		
		
		return returnVal;
		
	}
	
	public static RAMEntries getRAMEntry(int frame) {
		return RAM[frame];
	}
	
	public static String getValue(int frame, String offset) {
		RAM[frame].setRefBit("1");
		
		try {
		return RAM[frame].getValue(getOffSetInDecimal(offset));
		} catch (Exception e) {
			return null;
		}
		
	}

	public static String writeToMemory(int frame, String offset, String value) {
		RAM[frame].write(getOffSetInDecimal(offset), value);
		RAM[frame].setValidBit("1");
		RAM[frame].setRefBit("1");
		RAM[frame].setDirtyBit("1");
		return value;
	}
	
	public static Boolean isMemoryFull() {
		
		return frameNumber == Integer.MAX_VALUE; 
	}
	
	
	private static int getOffSetInDecimal(String offset) {
		return Integer.decode("0x" + offset);
	}
	
	public static int getFrameNumber() {
		return frameNumber;
	}
	
	public static int getNextAvailableFrame() {

		for (int i = 0; i < RAM.length; i++) {
			if (RAM[i] == null)
				return i;
		}
		return Integer.MAX_VALUE;
	}

	public static RAMEntries[] getRAM() {
		return RAM;
	}
	
	public static void setRefBit(int frame, String value) {
		RAM[frame].setRefBit(value);
	}
	
	public static String getRefBit(int frame) {
		return RAM[frame].getRefBit();
	}
	
	public static void setRAM(int frame, String[] entry) {
		RAM[frame].setRAMItem(entry);
	}
	
}










