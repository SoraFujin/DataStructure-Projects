package project0;



public class List<T extends Comparable<T>> implements ListInterface<T> {

	T[] arr;
	int count = 0;

	public List(int size) {
		if (size > 0) {
			arr = (T[]) new Comparable[size];
		} else {
			System.out.println("Error");
			arr = (T[]) new Comparable[size];
		}
	}

	@Override
	public void add(T data) {
		if (count < arr.length) {
			arr[count++] = data;
		} else {
			System.out.println("Error");
		}

	}

	@Override
	public void delete(T data) {
		int index = find(data);
		if (index == -1) {
			System.out.println("error");
		}
	}

	@Override
	public void set(T data, int index) {
		if (index > 0 && index < arr.length) {
			arr[index] = data;
		} else
			System.out.println("error");

	}

	@Override
	public int find(T data) {
		for (int i = 0; i < count; i++) {
			if (data.compareTo(arr[i]) == 0) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public void print() {
		for (int i = 0; i < count; i++) {
			System.out.println(arr[i].toString());
		}

	}

	@Override
	public int size() {
		return count;
	 
	}
}
