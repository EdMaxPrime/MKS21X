import java.util.*;

public class SuperArrayIterator implements Iterator<String> {
    private int current;
    private SuperArray sa;

    public SuperArrayIterator(SuperArray superarray) {
	current = 0;
	sa = superarray;
    }

    public boolean hasNext() {
	return sa.getSize() > current + 1;
    }

    public String next() throws NoSuchElementException {
	if(current < sa.getSize()) {
	    current++;
	    return sa.get(current - 1);
	}
	throw new NoSuchElementException("Iterator is done.");
    }

    public void remove() {}
}
