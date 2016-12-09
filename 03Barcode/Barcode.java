import java.util.Random;

public class Barcode implements Comparable<Barcode> {
    private String _zip;
    private int _checkDigit;

    public Barcode(String zip) {
	if(zip.length() != 5) {
	    throw new IllegalArgumentException("ZIP code must have a length of 5, recieved " + zip);
	}
	for(int i = 0; i < 5; i++) {
	    if(!Character.isDigit(zip.charAt(i))) {
		throw new IllegalArgumentException("ZIP code must be a 5 digit whole number, recieved " + zip);
	    }
	}
	_zip = zip;
	_checkDigit = checkSum();
    }

    public Barcode clone() {
	return new Barcode(_zip);
    }

    private int checkSum() {
	int sum = 0;
	for(int i = 0; i < _zip.length(); i++) {
	    sum += Integer.parseInt(_zip.substring(i, i+1));
	}
	return sum % 10;
    }

    public int compareTo(Barcode other) {
	return getCompareValue() - other.getCompareValue();
    }

    public String toString() {
	String encoded = "";
	for(int i = 0; i < _zip.length(); i++) {
	    encoded += encode(Integer.parseInt(_zip.substring(i, i+1)));
	}
	return _zip + _checkDigit + " |" + encoded + "|";
    }

    public static String toCode(String zip) {
	String code = "";
	if(zip.length() != 5) {
	    throw new IllegalArgumentException("Expected a ZIP code with 5 digits, instead found " + zip);
	}
	char[] chars = zip.toCharArray();
	for(char c : chars) {
	    if(Character.isDigit(c)) {
		code += encode(Integer.valueOf(""+c));
	    }
	    else {
		throw new IllegalArgumentException("Expected a ZIP code with 5 digits, but found non-digit '"+c+"' in " + zip);
	    }
	}
	return "|" + code + "|";
    }

    /*HELPER FUNCTIONS*/

    private static String encode(int digit) {
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

    /**
       The inverse of encode(). Converts a postal code into its digit
       @param postal the 5 character postal code representation of the digit
       @return A digit from 0-9 if valid, -1 if invalid
     */
    private static int decode(String postal) {
	int digit = 0;
	while(digit < 10) {
	    if(postal.equals(encode(digit))) {return digit;}
	    digit++;
	}
	return -1;
    }

    public int getCheckDigit() {return _checkDigit;}

    private int getCompareValue() {return Integer.parseInt(_zip) * 10 + _checkDigit;}
    
    /**
       Pads the first string using the second string until its length is >= width.
       If width is negative, adds to the beginning. If positive, adds to the end.
       @returns the padded string, atleast abs(width) characters long
     */
    public static String pad(String orig, String padding, int width) {
        while(orig.length() < Math.abs(width)) {
            if(width > 0) {
                orig += padding;
            } else {
                orig = padding + orig;
            }
	}
        return orig;
    }

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
	Barcode b = new Barcode("11234");
	System.out.println(b);
	System.out.println("a __ b  ==>  " + a.compareTo(b));
	System.out.println("a __ a  ==>  " + a.compareTo(a));
	System.out.println("a __ ~a ==>  " + a.compareTo(a.clone()));
	System.out.println("b __ a  ==>  " + b.compareTo(a));
	System.out.println("11234 to code ==> " + Barcode.toCode("11234"));
	Random rng = new Random();
	for(int test = 0; test < 5; test++) {
	    System.out.println(pad("", "~", 5) + "TEST " + test + pad("", "~", 5));
	    Barcode c = new Barcode(pad("" + rng.nextInt(100000), "0", -5)),
		d = new Barcode(pad("" + rng.nextInt(100000), "0", -5));
	    System.out.println("C: " + c);
	    System.out.println("D: " + d);
	    System.out.println("C __ D  ==>  " + c.compareTo(d));
	    System.out.println("C __ ~C ==>  " + c.compareTo(c.clone()));
	    System.out.println("D __ C  ==>  " + d.compareTo(c));
	}
    }
}
