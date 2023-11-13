package application;

public interface ListInterface<T extends Comparable<T>> {

	void add(T data);

	void delete(T data);

	T set(int index);

	int find(T data);

	void print();

	int size();
}
