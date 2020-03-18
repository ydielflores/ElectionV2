package DataStructures.Sets;

public interface SetFactory<E> {

	public Set<E> newInstance(int capacity);

}