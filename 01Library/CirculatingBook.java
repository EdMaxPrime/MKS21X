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

    public void checkout(String patron, String due) {}

    public void returned() {}

    public String circulationStatus() {return null;}

    public String toString() {return super.toString();}
}
