public class Book {
    private String author, title, isbn;
    
    public Book() {}

    public Book(String auth, String t, String booknum) {
	author = auth;
	title = t;
	isbn = booknum;
    }

    public String getAuthor() {return author;}
    public void setAuthor(String a) {author = a;}
    public String getTitle() {return title;}
    public void setTitle(String t) {title = t;}
    public String getISBN() {return isbn;}
    public void setISBN(String i) {isbn = i;}

    public String toString() {
	return author + ", " + title + ", " + isbn;
    }
}