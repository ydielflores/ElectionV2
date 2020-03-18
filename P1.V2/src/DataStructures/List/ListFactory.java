package DataStructures.List;

public interface ListFactory<E> {

	public List<E> newInstance(int initialCapacity);
	
	public List<E> newInstance();
}