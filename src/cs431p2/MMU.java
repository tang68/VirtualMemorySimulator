package cs431p2;

import java.util.Arrays;

public class MMU {
	
	String isSoftMiss;
	String isHardMiss;
	String isHit;
	
	public MMU() {
		isSoftMiss = "0";
		isHardMiss = "0";
		isHit = "0";
	}
	
	/*@return an array contains the result to output to CSV file
	 * The array has
	 * [0] Address
	 * [1] read or write
	 * [2] value
	 * [3] soft 
	 * [4] hard
	 * [5] hit
	 * [6] evicted page number
	 * [7] dirty evicted page 
	 * @param memoryAccess- array contains info about the access
	 * [0] read or write
	 * [1] address
	 * [2] only available if value of [0] is 1*/
	public String[] processMemoryAccess(String[] memoryAccess) {
		
		String[] result = new String[8];
		
		result[0] = memoryAccess[1];
		result[1] = memoryAccess[0];
		result[2] = getValueFromPageTable();
		result[3] = isSoftMiss;
		result[4] = isHardMiss;
		result[5] = isHit;
		result[6] = "evicted page num";
		result[7] = "dirty bit of the evicted page";
		
		resetSoftHardHit();
		return result;
		
	}
	
	/*Look up the TLB first, then look up page table if necessary  */
	private String getValueFromPageTable() {
		
		return null;
	}
	
	private void resetSoftHardHit() {
		
		isSoftMiss = "0";
		isHardMiss = "0";
		isHit = "0";
	}

}
