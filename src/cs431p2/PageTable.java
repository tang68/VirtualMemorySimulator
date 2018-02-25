package cs431p2;

public class PageTable {
	
	private PageTableEntries[] pageTable;
	//private int pageIndex;
	
	
	public PageTable() {
		//pageIndex = 0;
		pageTable = new PageTableEntries[256];
	}

	public String searchPageTable(String address) {
		
		String virtualPageNumber = address.substring(0, 2);
		String offset = address.substring(2, 4);
		
		int intVirtualPageNum = Integer.decode("0x" + virtualPageNumber);
		
		if (pageTable[intVirtualPageNum] != null &&
				pageTable[intVirtualPageNum].getValidBit() == "1") {
			return PhysicalMemory.getValue(pageTable[intVirtualPageNum].getPageFrameNum(), offset);
		}
			
		else {
			//add new item to page table
			pageTable[intVirtualPageNum] = new PageTableEntries("1", "1", "0", PhysicalMemory.getFrameNumber());
			return null;
		}
		
		
		
	}
	

}
