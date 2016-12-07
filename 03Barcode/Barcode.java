public class Barcode {
    private String _zip;
    private int _checkDigit;

    public Barcode(String zip) {
	if(zip.length() != 5) {
	    throw new IllegalArgumentException("ZIP code must have a length of 5");
	}
	for(int i = 0; i < 5; i++) {
	    if(!Character.isDigit(zip.charAt(i))) {
		throw new IllegalArgumentException("ZIP code must be a 5 digit whole number");
	    }
	}
	_zip = zip;
	_checkDigit = checkSum();
    }

    public Barcode clone() {return null;}

    private int checkSum() {return 0;}

    public int compareTo(Barcode other) {return 0;}

    public String toString() {return "";}
}
