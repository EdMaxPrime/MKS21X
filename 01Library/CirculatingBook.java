public class CirculatingBook extends LibraryBook {
    private String currentHolder;
    private String dueDate;

    public CirculatingBook(String auth, String t, String _isbn, String call) {
	super(auth, t, _isbn, call);
    }

    public void checkout(String patron, String due) {}

    public void returned() {}

    public String circulationStatus() {return null;}

    public String toString() {return super.toString();}
}
