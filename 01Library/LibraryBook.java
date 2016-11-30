public abstract class LibraryBook extends Book implements Comparable<LibraryBook> {
    private String callNumber;
    
    public LibraryBook(String auth, String t, String i, String c) {
	super(auth, t, i);
	callNumber = c;
    }

    public abstract void checkout(String patron, String due);
    public abstract void returned();
    public abstract String circulationStatus();
    
    public int compareTo(LibraryBook book) {
	return callNumber.compareTo(book.callNumber);
    }

    public String toString() {
	return super.toString() + ", " + circulationStatus() + ", " + callNumber;
    }
}
