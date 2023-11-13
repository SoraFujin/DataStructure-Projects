package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class USAName implements Comparable<USAName> {
	private String name;
	private char gender;
	private List<YearFreq> myList = new List<YearFreq>(50);

	public USAName(String name, char gender) {
		this.name = name;
		this.gender = gender;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public List<YearFreq> getMyList() {
		return myList;
	}

	public void setMyList(List<YearFreq> myList) {
		this.myList = myList;
	}

	@Override
	public int compareTo(USAName o) {
		if (o.name == this.name) {
			return 0;
		} else
			return -1;
	}

	@Override
	public String toString() {
		return "name= " + name + ", gender= " + gender + ", myList= " + myList;
	}

	public void addYearFreq(int year, int Freq) {
		myList.add(new YearFreq(year, Freq));
	}

	public static Object getMax(List list) {
		int maxFrequency = 0;
		Object maxFreq = null;

		for (int i = 0; i < list.size(); i++) {
			Object currentElement = list.find(i);
			int currentFrequency = 1; // Initialize the frequency to 1 for the current element

			for (int j = i + 1; j < list.size(); j++) {
				if (currentElement.equals(list.find(j))) {
					currentFrequency++;
				}
			}

			if (currentFrequency > maxFrequency) {
				maxFrequency = currentFrequency;
				maxFreq = currentElement;
			}
		}

		return maxFreq;
	}

}
