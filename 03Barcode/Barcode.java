public class Barcode {
    private String _zip;
    private int _checkDigit;

    public Barcode(String zip) {}

    public Barcode clone() {return null;}

    private int checkSum() {return 0;}

    public int compareTo(Barcode other) {return 0;}

    public String toString() {return "";}
}
