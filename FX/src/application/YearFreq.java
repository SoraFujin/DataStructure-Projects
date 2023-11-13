package application;

public class YearFreq implements Comparable<YearFreq> {
	private int year;
	private int freq;

	public YearFreq(int year, int freq) {
		this.year = year;
		this.freq = freq;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (year > 0) {
			this.year = year;
		} else
			System.out.println("year must be positive");
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		if (freq > 0) {
			this.freq = freq;
		} else
			System.out.println("frequency must be larger than zero");

	}

	@Override
	public String toString() {
		return year + ", " + freq;
	}

	@Override
	public int compareTo(YearFreq o) {
		if (this.year > o.getYear()) {
			return 1;
		} else if (this.year == o.getYear()) {
			return 0;
		}
		return -1;
	}
}
