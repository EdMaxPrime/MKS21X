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

    private int checkSum() {
	int sum = 0;
	for(int i = 0; i < _zip.length(); i++) {
	    sum += Integer.parseInt(_zip.substring(i, i+1));
	}
	return sum % 10;
    }

    public int compareTo(Barcode other) {return 0;}

    public String toString() {
	String encoded = "";
	for(int i = 0; i < _zip.length(); i++) {
	    encoded += encode(Integer.parseInt(_zip.substring(i, i+1)));
	}
	return _zip + _checkDigit + " |" + encoded + "|";
    }

    /*HELPER FUNCTIONS*/

    private String encode(int digit) {
	switch(digit) {
	case 1:
	    return ":::||";
	case 2:
	    return "::|:|";
	case 3:
	    return "::||:";
	case 4:
	    return ":|::|";
	case 5:
	    return ":|:|:";
	case 6:
	    return ":||::";
	case 7:
	    return "|:::|";
	case 8:
	    return "|::|:";
	case 9:
	    return "|:|::";
	case 0:
	    return "||:::";
	}
	return "";
    }

    public int getCheckDigit() {return _checkDigit;}

    public static void main(String[] args) {
	Barcode a;
	try {a = new Barcode("");}
	catch(Exception e) {System.out.println("Passed empty string test");}
	try {a = new Barcode("abcde");}
	catch(Exception e) {System.out.println("Passed NaN test");}
	try {a = new Barcode("1");}
	catch(Exception e) {System.out.println("Passed too short test");}
	a = new Barcode("06780");
	System.out.println(a.getCheckDigit());
	System.out.println(a);
    }
}
