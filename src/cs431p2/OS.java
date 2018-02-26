package cs431p2;

public class OS {
	
	
	//put a new page to physical memory, evict a page using the clock algorithm if necessary
	public String bringPageToMemory(String[] memoryAccess) {
		
		//check to see if we need to evict a page
//		if (PhysicalMemory.isMemoryFull()) {
//			evict();
//		}
		
		//
		String value = PhysicalMemory.addNewFrame(memoryAccess);

		return value;
	}
	
	//return index of the evicted frame
	public int evict() {
		
		//clock alg to determine which frame to evict
		//write RAM[frameToEvict] to .pg file
		//set RAM[frameToEvict]  to null
		//return frameToEvict
		
		return 0;
	}

}
