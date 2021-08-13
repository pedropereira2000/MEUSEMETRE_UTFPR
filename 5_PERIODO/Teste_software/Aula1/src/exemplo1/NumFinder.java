package exemplo1;

public class NumFinder {
	private int smallest = Integer.MAX_VALUE;
	private int largest = Integer.MIN_VALUE;

	public int getSmallest() {
		return smallest;
	}

	public int getLargest() {
		return largest;
	}

	public void find(int[] nums) {
		for (int n : nums) {
			if (n < smallest)
				smallest = n;
			
			if (n > largest)
				largest = n;
		}
	}
}