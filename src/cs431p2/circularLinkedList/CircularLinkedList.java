package cs431p2.circularLinkedList;


public class CircularLinkedList<T> implements CircularLinkedListInterface<T>{
	
	private Node<T> firstNode;
	private Node<T> lastNode;
	int numberOfEntries;
	
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
		
		@SuppressWarnings("unused")
		public T getData(){
			return data;
		}
		
		@SuppressWarnings("unused")
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

	@Override
	public void replaceNode(T item,T lookingFor) {
		//Get the lookingFor item referece
		Node<T> ref = getReference(lookingFor);
		if(ref!=null){
			//replace it 
			ref.setData(item);
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

	/*
	 * Just testing it is circular linkedlist
	 */
	public void keepToRun(){
		Node<T> current = firstNode;
		while(true){
			System.out.println(current.getData()+" ");
			current = current.next;
		}
	}

}
