package cs431p2;

public class PageTable {
	
	private PageTableEntries[] pageTable;
	private int pageIndex;
	
	
	public PageTable() {
		pageIndex = 0;
		pageTable = new PageTableEntries[256];
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

}
