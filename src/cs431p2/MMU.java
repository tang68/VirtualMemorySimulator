package cs431p2;

public class MMU {
	
	public MMU() {
		
		
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
		
		String[] s = {"1", "2", "3", "4", "5", "6", "7", "8" };
		return s;
	}

}
