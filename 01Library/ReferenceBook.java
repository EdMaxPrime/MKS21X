public class ReferenceBook extends LibraryBook {
    private String collection;
    
    public ReferenceBook(String auth, String t, String _isbn, String call, String c) {
	super(auth, t, _isbn, call);
	collection = c;
    }

    public void checkout(String patron, String due) {
	System.out.println("Cannot check out a reference book");
    }

    public void returned() {
	System.out.println("Reference book could not have been checked out -- return impossible");
    }

    public String circulationStatus() {
	return "Non-circulating reference book";
    }

    public String toString() {
	return super.toString() + ", " + collection + " collection";
    }
}
