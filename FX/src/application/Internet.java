package application;

import java.util.ArrayList; // Import the ArrayList class

public class Internet implements Comparable<Internet> {
    private String name;
    private double percent;
    private List<Internet> myList;

    public List<Internet> getData() {
		return myList;
	}

	public void setData(List<Internet> data) {
		this.myList = data;
	}

	public Internet(String name, double percent) {
        this.name = name;
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Override
    public int compareTo(Internet other) {
        return this.name.compareTo(other.getName());
    }

	@Override
	public String toString() {
		return "name= " + name + ", percent= " + percent;
	}

    
}