public class CirculatingBook extends LibraryBook {
    private String currentHolder;
    private String dueDate;

    public CirculatingBook(String auth, String t, String _isbn, String call) {
	super(auth, t, _isbn, call);
    }

    public String getCurrentHolder() {return currentHolder;}

    public String getDueDate() {return dueDate;}

    public void setCurrentHolder(String h) {currentHolder = h;}

    public void setDueDate(String d) {dueDate = d;}

    public void checkout(String patron, String due) {
	currentHolder = patron;
	dueDate = due;
    }

    public void returned() {
	currentHolder = null;
	dueDate = null;
    }

    public String circulationStatus() {
	if(currentHolder == null || dueDate == null) {
	    return ("Book available on shelves");
	}
	return ("Held by " + currentHolder + " until " + dueDate);
    }

    public String toString() {
	return super.toString() + ", holder: " + currentHolder + ", due: " + dueDate;
    }
}
