package cs431p2;

public class RAMEntries {

	private String[] r;
	private String virtualPage;
	private String validBit;
	private String refBit;
	private String dirtyBit;

	public RAMEntries() {
		r = new String[256];
		virtualPage = "";
		validBit = "1";
		refBit = "1";
		dirtyBit = "0";
	}

	public void setRAMItem(String[] arr) {
		r = arr;
	}

	public String getValueWrittenToMemory(int offset) {
		return r[offset];
	}

	public void write(int offset, String value) {
		r[offset] = value;
	}

	public String getValue(int offset) {
		return r[offset];
	}

	public String getValidBit() {
		return validBit;
	}

	public String getVirtualPage() {
		return virtualPage;
	}

	public void setVirtualPage(String virtualPage) {
		this.virtualPage = virtualPage;
	}

	public void setValidBit(String validBit) {
		this.validBit = validBit;
	}

	public String getRefBit() {
		return refBit;
	}

	public void setRefBit(String refBit) {
		this.refBit = refBit;
	}

	public String getDirtyBit() {
		return dirtyBit;
	}

	public void setDirtyBit(String dirtyBit) {
		this.dirtyBit = dirtyBit;
	}

	public String toString() {
		return validBit + "--" + refBit + "--" + dirtyBit + " * ";
	}



}
