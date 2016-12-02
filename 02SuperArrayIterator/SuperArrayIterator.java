import java.util.Iterator;

public class SuperArrayIterator implements Iterator<String> {
    private int current;
    private SuperArray sa;

    public SuperArrayIterator(SuperArray superarray) {
	current = 0;
	sa = superarray;
    }
}
