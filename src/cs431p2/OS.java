package cs431p2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import cs431p2.circularLinkedList.*;

public class OS {
	
	private static CircularLinkedListInterface<RAMEntries> clock = 
			new CircularLinkedList<>();
	private static int clockHand = 0;
	
	//put a new page to physical memory, evict a page using the clock algorithm if necessary
	public static String[] bringPageToMemory(String[] memoryAccess) {
		
		//check to see if we need to evict a page
		String[] values = {"", "", ""};
		if (PhysicalMemory.isMemoryFull()) {
			int frame = evict();
			values = PhysicalMemory.addDataToMemory(memoryAccess, frame);

		}
		else {
			values = PhysicalMemory.addDataToMemory(memoryAccess, PhysicalMemory.getFrameNumber());
		}
		return values;
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
		
		String[] data = entry.getR();
		String virtualPage = entry.getVirtualPage();
		String filePath = "Project2_test_and_page_files/page_files/" + virtualPage + ".pg";
		try {
			PrintWriter pw = new PrintWriter(filePath);
			pw.close();
		} catch (Exception e) {
			System.out.println("****** Unable to remove data from disk in order to write");
		}
		
		try {
			BufferedWriter outputWriter = null;
			  outputWriter = new BufferedWriter(new FileWriter(filePath));
			  for (int i = 0; i < data.length; i++) {
			    outputWriter.write(data[i]);
			    outputWriter.newLine();
			  }
			  outputWriter.flush();  
			  outputWriter.close();  
			
		} catch (Exception e) {
			System.out.println("******* Unable to write data from memory to disk");
		}
		
		
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
