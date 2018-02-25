package cs431p2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TLB {
	
	
	private TLBEntries[] TLBuffer;
	private int TLBIndex;
	
	public TLB() {
		TLBIndex = 0;
		TLBuffer = new TLBEntries[8];
	}
	
	/*
	 * Search TLB for given address
	 * if a match found, get data using page frame number
	 * otherwise, add new entries to TLB with the current TLBIndex, 
	 * return null to indicate soft miss
	 */
	public String searchTLB(String[] memoryAccess) {
		
		String address = memoryAccess[1];
		Boolean isRead = memoryAccess[0].equals("0");
		String virtualPageNumber = address.substring(0, 2);
		String offset = address.substring(2, 4);
		
		for (int i = 0; i < TLBuffer.length; i++) {

			if (TLBuffer[i] == null)
				break;
			
			if (TLBuffer[i].getVirtualPageNum().equals(virtualPageNumber)) {

				//take the page frame # and the offset, go to physical memory to get value
				int frameNumber = TLBuffer[i].getPageFrameNum();
				if(isRead)
					return PhysicalMemory.getValue(frameNumber, offset);
				else
					return PhysicalMemory.writeToMemory(frameNumber, offset, memoryAccess[2]);
			}
		}
		
		if (TLBIndex == 8)
			TLBIndex = 0;
		
		TLBuffer[TLBIndex] = new TLBEntries(virtualPageNumber, "1", "1", "0", 
				PhysicalMemory.getFrameNumber());
		TLBIndex++;
		
		return null;
	}

	
	public int getTLBIndex() {
		return TLBIndex;
	}

	public void setTLBIndex(int tLBIndex) {
		TLBIndex = tLBIndex;
	}
	

}
