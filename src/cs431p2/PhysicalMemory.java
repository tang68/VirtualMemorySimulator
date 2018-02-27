package cs431p2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhysicalMemory {

	private static RAMEntries RAM = new RAMEntries();
	private static final String pagesFilePath = "Project2_test_and_page_files/page_files/";
	
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
			
			RAM.setRAMItem(frame, tmpRAMItem);
			
			if (memoryAccess[0].equals("1"))
				RAM.writeNewValueToRam(frame, getOffSetInDecimal(offset), memoryAccess[2]);

			returnVal = RAM.getValueWrittenToMemory(frame, getOffSetInDecimal(offset));
			RAM.setFrameNum(RAM.getNextAvailableFrame());

		} catch (FileNotFoundException ex) {
			System.out.println("***** Unable to open file");
		} finally {
			scanFiles.close();
		}
		
		return returnVal;
		
	}
	
	public static String getValue(int frame, String offset) {
		return RAM.getValue(frame, getOffSetInDecimal(offset)); 
	}

	public static String writeToMemory(int frame, String offset, String value) {
		//RAM[frame][getOffSetInDecimal(offset)] = value;
		RAM.write(frame, getOffSetInDecimal(offset), value);
		return value;
	}
	
	public static Boolean isMemoryFull() {
		
		return RAM.getFrameNum() == Integer.MAX_VALUE;
	}
	
	private static int getOffSetInDecimal(String offset) {
		return Integer.decode("0x" + offset);
	}
	
	public static int getFrameNumber() {
		return RAM.getFrameNum();
	}
	
	public void setFrameNum(int frameNum) {
		RAM.setFrameNum(frameNum); 
	}

	public String getValidBit() {
		return RAM.getValidBit();
	}

	public void setValidBit(String validBit) {
		RAM.setValidBit(validBit); 
	}

	public String getRefBit() {
		return RAM.getRefBit();
	}

	public void setRefBit(String refBit) {
		RAM.setRefBit(refBit);
	}

	public String getDirtyBit() {
		return RAM.getDirtyBit();
	}

	public void setDirtyBit(String dirtyBit) {
		RAM.setDirtyBit(dirtyBit);
	}
	
}










