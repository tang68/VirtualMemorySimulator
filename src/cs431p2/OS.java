package cs431p2;

public class OS {
	
	
	//put a new page to physical memory, evict a page using the clock algorithm if necessary
	public String bringPageToMemory(String address) {
		
		//check to see if we need to evict a page
		
//		if (PhysicalMemory.isMemoryFull()) {
//			
//			evict();
//		}
		
		String value = PhysicalMemory.addNewFrame(address);
		
		
		
		return value;
	}

}
