package cs431p2;

public class RAMEntries {

	private String[][] r;
	private int frameNum;
	private String validBit;
	private String refBit;
	private String dirtyBit;

	public RAMEntries() {
		r = new String[16][256];
		frameNum = 0;
		validBit = "0";
		refBit = "0";
		dirtyBit = "0";
	}

	public void setRAMItem(int index, String[] arr) {
		r[index] = arr;
	}

	public void writeNewValueToRam(int frame, int offset, String value) {
		r[frame][offset] = value;
	}

	public String getValueWrittenToMemory(int frame, int offset) {
		return r[frame][offset];
	}

	public int getNextAvailableFrame() {

		for (int i = 0; i < r.length; i++) {
			if (r[i][0] == null)
				return i;
		}
		return Integer.MAX_VALUE;
	}

	public void write(int frame, int offset, String value) {
		r[frame][offset] = value;
	}

	public String getValue(int frame, int offset) {
		return r[frame][offset];
	}

	public int getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(int frameNum) {
		this.frameNum = frameNum;
	}

	public String getValidBit() {
		return validBit;
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



}
