package cs431p2.circularLinkedList;


public class CircularLinkedList<T> implements CircularLinkedListInterface<T>{
	
	private Node<T> firstNode;
	private Node<T> lastNode;
	int numberOfEntries;
	
	@SuppressWarnings("hiding")
	private class Node<T>{
		private T data;
		private Node<T> next;
		
		private Node (T dataPortion){
			this(dataPortion,null);
		}
		private Node(T dataPortion, Node<T> nextNode){
			data = dataPortion;
			next = nextNode;
		}
		
		public T getData(){
			return data;
		}
		
		public void setData(T newData){
			data = newData;
		}
		
		@SuppressWarnings("unused")
		public Node<T> getNextNode(){
			return next;
		}
		
		@SuppressWarnings("unused")
		public void setNextNode(Node<T> nextNode){
			next = nextNode;
		}
	}
	
	public CircularLinkedList(){
		firstNode = null;
		lastNode = null;
		numberOfEntries = 0;
	}
	/**
	 * Insert the element to the head
	 */
	@Override
	public void insert(T item) {
		Node<T> newNode = new Node<>(item);
		if(firstNode == null){
			newNode.next = firstNode;
			firstNode = newNode;
			lastNode = firstNode;
		}
		else{
			lastNode.next = newNode;
			newNode.next = firstNode;
			firstNode = newNode;
		}
		numberOfEntries++;
		
	}

	/**
	 * remove the first element from the head
	 */
	@Override
	public T remove() {
		T result = null;
		if(firstNode != null){
			result = (T)firstNode.data;
			firstNode= firstNode.next;
			lastNode.next = firstNode;
			numberOfEntries--;
		}
		return result;
	}
	/**
	 * Get the reference by given the entry
	 * @param Entry
	 * @return the reference of the entry
	 */
	private Node<T> getReference(T Entry){
		Node<T> currentNode = firstNode;
		int runCount = 0;
		boolean found = false;
		
		while(!found && (currentNode != null)&&(runCount < numberOfEntries)){
			if(Entry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
			runCount++;
		}
		return found? currentNode : null;
	}
	
	/**
	 * Give the item by given the index
	 * index start by 1, 2,3,4....
	 * @param index
	 * @return Reference
	 */
	@Override
	public T getItemByIndex(int index){
		Node<T> currentNode = firstNode;
		for(int i=0;i<index;i++){
			currentNode = currentNode.next;
		}
		return currentNode.getData();
	}
	
	/**
	 * Replacement by given Index
	 * @param newItem
	 * @param index
	 */
	@Override
	public void replaceByGivenIndex(T newItem,int index){
		replaceByGivenItem(newItem,getItemByIndex(index));
	}

	/**
	 * Replacement by given item to searching
	 * item  =  the new item 
	 * lookingFor = the old item which you want to replace
	 */
	@Override
	public void replaceByGivenItem(T newItem,T lookingFor) {
		//Get the lookingFor item reference
		Node<T> ref = getReference(lookingFor);
		if(ref!=null){
			//replace it 
			ref.setData(newItem);
		}
	}
	
	@Override
	public boolean isEmpty() {
		return firstNode == null && lastNode == null;
	}

	@Override
	public void clear() {
		if(!isEmpty())
			remove();
	}

	@Override
	public int getSize() {
		return numberOfEntries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[numberOfEntries];
		int index = 0;
		Node<T> currentNode = firstNode;
		while( (index< numberOfEntries) && (currentNode != null)){
			result[index] = (T) currentNode.data;
			index++;
			currentNode=currentNode.next;
		}
		return result;
	}	

}
