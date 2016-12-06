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

    public boolean isDone1() {
	int last = 0;
	for(int row = 0; row < side; row++) {
	    for(int col = 0; col < side; col++) {
		if(values[row][col] > last) {
		    last++;
		} else if(values[row][col] != 0) {
		    return false;
		}
	    }
	}
	return true;
    }

    public boolean isDone2() {
	for(int row = 0; row < side; row++) {
	    for(int col = 0; col < side; col++) {
		if( !(col == 0 && row == 0) && !(col+1 == side && row+1 == side) ) {
		    int linear = row * side + col;
		    int prev = values[(linear-1) / 4][(linear-1) % 4];
		    int next = values[(linear+1) / 4][(linear+1) % 4];
		    //System.out.println("[" + linear + "] for " + prev + " - " + values[row][col] + " - " + next);
		    /*if((values[row][col] == 0 && prev > next) ||
		       (prev == 0 && values[row][col] > next) ||
		       (next == 0 && values[row][col] < prev)) {
			return false;
			}*/
		    if((values[row][col] != 0 && next != 0 && prev != 0) && prev > next) {
			return false;
		    }
		}
	    }
	}
	return true;
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
	System.out.println(s.isDone1());
	s.solve(0);
	System.out.println(s);
	System.out.println(s.isDone1());
	s.solve(12);
	System.out.println(s);
	System.out.println(s.isDone1());
	System.out.println("==2nd Version==");
	s = new SlidingPuzzle(4);
	System.out.println(s);
	System.out.println(s.isDone2());
	s.solve(0);
	System.out.println("Hole@0: " + s.isDone2());
	s.solve(15);
	System.out.println("Hole@15: " + s.isDone2());
	s.solve(12);
	System.out.println("Hole@12: " + s.isDone2());
    }
}