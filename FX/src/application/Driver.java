package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		List<Internet> myList = new List<>(500);
		Scanner scanner = new Scanner(System.in);

		try {
			Scanner scanner1 = new Scanner(new File("internet_2020.txt"));
			while (scanner1.hasNextLine()) {
				String s[] = scanner1.nextLine().split(",");
				if (s.length == 2) {
					Internet net = new Internet(s[0], Double.parseDouble(s[1].trim()));
					myList.add(net);
				}
			}
			scanner1.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		myList.print();
	}
}
