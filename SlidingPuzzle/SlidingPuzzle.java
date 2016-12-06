import java.util.*;

public class SlidingPuzzle {
    private int side;
    private int[][] values;

    public SlidingPuzzle(int sidelength) {
	side = sidelength;
	values = new int[side][side];
    }

    public void initialize() {
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	for(int n = 0; n < side * side; n++)
	    numbers.add(new Integer(n));
	for(int row = 0; row < side; row++) {
	    for(int col = 0; col < side; col++) {
		int index = (new Random()).nextInt(numbers.size());
		values[row][col] = numbers.get(index);
		numbers.remove(index);
	    }
	}
    }

    public String toString() {
	String str = "";
	for(int row = 0; row < side; row++) {
	    for(int col = 0; col < side; col++) {
		str += (values[row][col] + " ");
	    }
	    str += "\n";
	}
	return str;
    }

    public static void main(String[] args) {
	SlidingPuzzle s = new SlidingPuzzle(4);
	System.out.println(s);
	//System.out.println();
    }
}