package cs431p2;

public class TLBEntries {
	
	private String virtualPageNum;
	private String validBit;
	private String refferenceBit;
	private String dirtyBit;
	private int pageFrameNum;
	
	public TLBEntries(String virtualPage, String valid, 
			String ref, String dirty, int frame) {
		
		virtualPageNum = virtualPage;
		validBit = valid;
		refferenceBit = ref;
		dirtyBit = dirty;
		pageFrameNum = frame;
	}
	
	public String toString() {
		return virtualPageNum + "-" + validBit + "-" + 
	refferenceBit + "-" + dirtyBit + "-" + pageFrameNum;
	}

	
	/*
	 *GETTERS SETTERS BLOCK 
	 */
	public String getVirtualPageNum() {
		return virtualPageNum;
	}

	public void setVirtualPageNum(String virtualPageNum) {
		this.virtualPageNum = virtualPageNum;
	}

	public String getValidBit() {
		return validBit;
	}

	public void setValidBit(String validBit) {
		this.validBit = validBit;
	}

	public String getRefferenceBit() {
		return refferenceBit;
	}

	public void setRefferenceBit(String refferenceBit) {
		this.refferenceBit = refferenceBit;
	}

	public String getDirtyBit() {
		return dirtyBit;
	}

	public void setDirtyBit(String dirtyBit) {
		this.dirtyBit = dirtyBit;
	}

	public int getPageFrameNum() {
		return pageFrameNum;
	}

	public void setPageFrameNum(int pageFrameNum) {
		this.pageFrameNum = pageFrameNum;
	}

	/*
	 *END GETTERS SETTERS BLOCK 
	 */
}
