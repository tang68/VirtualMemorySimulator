package circularLinkedList;

public interface CircularLinkedListInterface<T> {

	public boolean isEmpty();
	public void clear();
	public int getSize();
	public void insert(T item);
	public T remove();
	public void replaceNode(T item,T lookingFor);
	public T[] toArray();
}
