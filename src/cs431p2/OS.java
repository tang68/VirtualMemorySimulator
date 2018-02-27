package cs431p2;

import cs431p2.circularLinkedList.*;

public class OS {
	
	private static CircularLinkedListInterface<RAMEntries> clock = 
			new CircularLinkedList<>();
	private static int clockHand = 0;
	
	//put a new page to physical memory, evict a page using the clock algorithm if necessary
	public static String bringPageToMemory(String[] memoryAccess) {
		
		//check to see if we need to evict a page
		String value = "";
		if (PhysicalMemory.isMemoryFull()) {
			//System.out.println("******FULL need to evict");
			int frame = evict();
			value = PhysicalMemory.addDataToMemory(memoryAccess, frame);
		}
		else {
			value = PhysicalMemory.addDataToMemory(memoryAccess, PhysicalMemory.getFrameNumber());
		}
		return value;
	}
	
	//return index of the evicted frame
	public static int evict() {
		
		//clock alg to determine which frame to evict
		Boolean done = false;
		int evictedFrameIndex = 0;
		while (!done) {
			//System.out.println("Size " + clock.getSize() + " => " + clock);

			if (PhysicalMemory.getRefBit(clockHand).equals("1")) {

				PhysicalMemory.setRefBit(clockHand, "0");
				clockHand++;
				if (clockHand == 16)
					clockHand = 0;
			}
			
			else {
				//System.out.println(" EVICTED");
				evictedFrameIndex = clockHand;
				
				//write RAM[frameToEvict] to .pg file
				writeToPGFile(PhysicalMemory.getRAMEntry(evictedFrameIndex));
				if (evictedFrameIndex == 7)
					System.out.println("");
				
				PhysicalMemory.setRAM(evictedFrameIndex, null);

				done = true;
			}	
		}
		return evictedFrameIndex;
	}
	
	private static void writeToPGFile(RAMEntries entry) {
		
	}
	
	public static void updateClock() {
		
		RAMEntries[] ram = PhysicalMemory.getRAM();
		
		for (int i = 0; i < ram.length; i++) {
			
			if (ram[i] != null && !clock.containsNode(ram[i])) {
				clock.insert(ram[i]);
			}
		}
	}
	


	public static CircularLinkedListInterface<RAMEntries> getClock() {
		return clock;
	}

	public static void setClock(CircularLinkedListInterface<RAMEntries> clock) {
		OS.clock = clock;
	}
	

}
