package cs431p2.circularLinkedList;

public interface CircularLinkedListInterface<T> {
	public boolean isEmpty();
	public void clear();
	public int getSize();
	public void insert(T item);
	public T remove();
	public void replaceByGivenItem(T item,T lookingFor);
	public void replaceByGivenIndex(T newItem,int index);
	public T getItemByIndex(int index);
	public T[] toArray();
	public Boolean containsNode(T Entry);
	public String toString();
}
