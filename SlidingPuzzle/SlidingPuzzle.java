import java.util.*;

public class SlidingPuzzle {
    private int side;
    private int[][] values;

    public SlidingPuzzle(int sidelength) {
	side = sidelength;
	values = new int[side][side];
	initialize();
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
		str += pad("" + values[row][col], " ", -3);
	    }
	    str += "\n";
	}
	return str;
    }

    public void solve(int hole) {
	int last = 0;
	for(int row = 0; row < side; row++) {
	    for(int col = 0; col < side; col++) {
		if(row * 4 + col == hole) {values[row][col] = 0;}
		else {last++; values[row][col] = last;}
	    }
	}
    }

    public static String pad(String orig, String padding, int width) {
	while(orig.length() < Math.abs(width)) {
	    if(width > 0) {
		orig += padding;
	    } else {
		orig = padding + orig;
	    }
	}
	return orig;
    }

    public static void main(String[] args) {
	SlidingPuzzle s = new SlidingPuzzle(4);
	System.out.println(s);
	s.solve(0);
	System.out.println(s);
	s.solve(12);
	System.out.println(s);
    }
}