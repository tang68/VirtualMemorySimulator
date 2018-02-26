package cs431p2;

import cs431p2.circularLinkedList.*;

public class OS {
	
	private static CircularLinkedListInterface<PageTableEntries> clock = 
			new CircularLinkedList<>();
	
	//put a new page to physical memory, evict a page using the clock algorithm if necessary
	public static String bringPageToMemory(String[] memoryAccess) {
		
		//check to see if we need to evict a page
		String value = "";
		if (PhysicalMemory.isMemoryFull()) {
			System.out.println("******FULL need to evict");
			int frame = evict();
			value = PhysicalMemory.addDataToMemory(memoryAccess, frame);
		}
		else {
			value = PhysicalMemory.addDataToMemory(memoryAccess, PhysicalMemory.getFrameNumber());
		}
		return value;
	}
	
	//return index of the evicted frame
	private static int evict() {
		
		//clock alg to determine which frame to evict
		
		//write RAM[frameToEvict] to .pg file
	
		//set RAM[frameToEvict]  to null
		//return frameToEvict
		
		return 0;
	}
	
	public static void addItemToClock(PageTableEntries pte) {
		clock.insert(pte);
	}

	public static CircularLinkedListInterface<PageTableEntries> getClock() {
		return clock;
	}

	public static void setClock(CircularLinkedListInterface<PageTableEntries> clock) {
		OS.clock = clock;
	}
	

}
