package cs431p2;

public class TLB {
	
	private TLBEntries[] TLBuffer;
	private int TLBIndex;
	


	public TLB() {
		TLBIndex = 0;
		TLBuffer = new TLBEntries[8];
	}
	
	public int getTLBIndex() {
		return TLBIndex;
	}

	public void setTLBIndex(int tLBIndex) {
		TLBIndex = tLBIndex;
	}
	

}
