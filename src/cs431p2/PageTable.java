package cs431p2;

import java.util.Arrays;

public class PageTable {
	
	private PageTableEntries[] pageTable;
	//private int pageIndex;
	
	
	public PageTable() {
		//pageIndex = 0;
		pageTable = new PageTableEntries[256];
	}

	public String searchPageTable(String[] memoryAccess) {
		
		Boolean isRead = memoryAccess[0].equals("0");
		String address = memoryAccess[1];
		String virtualPageNumber = address.substring(0, 2);
		String offset = address.substring(2, 4);
		
		int intVirtualPageNum = Integer.decode("0x" + virtualPageNumber);
		
		if (pageTable[intVirtualPageNum] != null &&
				pageTable[intVirtualPageNum].getValidBit().equals("1")) {
			if(isRead) {
				System.out.println(intVirtualPageNum + " PT Index --Page Table -> " + 
						Arrays.toString(pageTable));
				return PhysicalMemory.getValue(pageTable[intVirtualPageNum].getPageFrameNum(), offset);
			}
			else {
			PhysicalMemory.writeToMemory(pageTable[intVirtualPageNum].getPageFrameNum(),
					offset, memoryAccess[2]);
			System.out.println(intVirtualPageNum + " PT Index --Page Table -> " + 
					Arrays.toString(pageTable));
			return memoryAccess[2];
			}	
		}
			
		else {
			//add new item to page table
			if (isRead) {
				pageTable[intVirtualPageNum] = new PageTableEntries("1", "1", "0", 
						PhysicalMemory.getFrameNumber());
				System.out.println(intVirtualPageNum + " PT Index --Page Table -> " + 
						Arrays.toString(pageTable));
			}
			
			else {
				pageTable[intVirtualPageNum] = new PageTableEntries("1", "1", "1", 
						PhysicalMemory.getFrameNumber());
				System.out.println(intVirtualPageNum + " PT Index --Page Table -> " + 
						Arrays.toString(pageTable));	
			}
			
			return null;
		}
		
		
		
	}
	

}
