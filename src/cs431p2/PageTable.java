package cs431p2;

import java.util.Arrays;

public class PageTable {
	
	private static PageTableEntries[] pageTable = new PageTableEntries[256];

	public static String searchPageTable(String[] memoryAccess) {
		
		Boolean isRead = memoryAccess[0].equals("0");
		String address = memoryAccess[1];
		String virtualPageNumber = address.substring(0, 2);
		String offset = address.substring(2, 4);
		
		int intVirtualPageNum = Integer.decode("0x" + virtualPageNumber);
		
		if (pageTable[intVirtualPageNum] != null &&
				pageTable[intVirtualPageNum].getValidBit().equals("1")) {
			if(isRead) {
//				System.out.println(intVirtualPageNum + " PT Index --Page Table -> " + 
//						Arrays.toString(pageTable));
				return PhysicalMemory.getValue(pageTable[intVirtualPageNum].getPageFrameNum(), offset);
			}
			else {
				try {
					PhysicalMemory.writeToMemory(pageTable[intVirtualPageNum].getPageFrameNum(),
							offset, memoryAccess[2]);
				} catch (Exception e) {
					OS.bringPageToMemory(memoryAccess);
					PhysicalMemory.writeToMemory(pageTable[intVirtualPageNum].getPageFrameNum(),
							offset, memoryAccess[2]);
				}
				

			return memoryAccess[2];
			}	
		}
			
		else {
			//add new item to page table
			if (isRead) {
				pageTable[intVirtualPageNum] = new PageTableEntries("1", "1", "0", 
						PhysicalMemory.getFrameNumber());
				//OS.addItemToClock(pageTable[intVirtualPageNum]);
//				System.out.println(intVirtualPageNum + " PT Index --Page Table -> " + 
//						Arrays.toString(pageTable));
			}
			
			else {
				pageTable[intVirtualPageNum] = new PageTableEntries("1", "1", "1", 
						PhysicalMemory.getFrameNumber());
				//OS.addItemToClock(pageTable[intVirtualPageNum]);
//				System.out.println(intVirtualPageNum + " PT Index --Page Table -> " + 
//						Arrays.toString(pageTable));	
			}
			
			return null;
		}
	}
	

	public static PageTableEntries[] getPageTable() {
		return pageTable;
	}

	

}
