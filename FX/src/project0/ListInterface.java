package project0;

public interface ListInterface<T extends Comparable <T> > {

	void add(T data);

	void delete(T data);

	void set(T data, int index);

	int find(T data);

	void print();

	int size();

}