import java.util.*;

public class SuperArray implements Iterable<String> {
    private String[] data;
    private int size;

    public SuperArray() {
        this(10);
    }

    public SuperArray(int initialCapacity) throws IllegalArgumentException {
        if(initialCapacity < 0) {
            throw new IllegalArgumentException("SuperArray expected an initial capacity greater than 0, instead got " + initialCapacity);
        }
        data = new String[initialCapacity];
        size = 0;
    }

    public boolean add(String element) {
        if(size < data.length) {
            data[size] = element;
            size++;
        }
        else {
            grow(); 
            add(element);
        }
        return true;
    }

    private void grow() {
        String[] bigger = new String[data.length * 2]; //double capacity
        for(int i = 0; i < data.length; i++) {
            bigger[i] = data[i];
        }
        data = bigger;
    }

    public int getSize() {return size;}

    public String toString() {
        if(size == 0) {return "[]";}
        String str = "[";
        for(int i = 0; i < size; i++) {
            str = str + "'" + data[i] + "', ";
        }
        return str.substring(0, str.length()-2) + "]";
    }

    public String toStringDebug() {
        String str = "[";
        for(int i = 0; i < data.length; i++) {
            if(i < size) {
                str = str + "'" + data[i] + "', ";
            }
            else {
                str += "_, ";
            }
        }
        return str.substring(0, str.length()-2) + "]";
    }

    //returns the element at the specified index
    public String get(int index) throws IndexOutOfBoundsException {
        if(index >= 0 && index < size) {
            return data[index];
        }
        else {
            throw new IndexOutOfBoundsException("Get");
        }
    }

    //clear array by "forgetting" all previous values
    public void clear() {
        size = 0;
    }

    //true if this list contains no elements
    public boolean isEmpty() {
        return size == 0;
    }

    //if index is [0,size] then add it and return the old value
    public String set(int index, String element) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("SuperArray.set expected an index from [0, size), instead got " + index);
        }
        String old = data[index];
        data[index] = element;
        return old;
    }

    //returns a copy of the internal array of length "size"
    public String[] toArray() {
        String[] copy = new String[size];
        for(int i = 0; i < size; i++) {
            copy[i] = data[i];
        }
        return copy;
    }

    //returns the first index of this element, -1 if it is not found
    public int indexOf(String element) {
        for(int i = 0; i < size; i++) {
            if(data[i].equals(element)) {return i;}
        }
        return -1;
    }

    //returns the last index of this element, -1 if it is not found
    public int lastIndexOf(String element) {
        for(int i = size - 1; i >= 0; i--) {
            if(data[i].equals(element)) {return i;}
        }
        return -1;
    }

    //used internally to shift elements left or right. To insert,
    //make sure the displacement is > 0, to delete make it < 0.
    //startIndex is the index of the first affected element.
    //assumes startIndex is a valid index, as is startIndex + displacement.
    private void shift(int startIndex, int displacement) {
        int diff = displacement;
        if(diff > 0 && size > 0) {
            for(int i = size; i > startIndex; i--) {
                if(i == size) {
                    //add(size, data[i - displacement]);
                    if(size >= data.length) {grow();}
                    data[size] = data[i - displacement];
                    size++;
                }
                else {data[i] = data[i - displacement];}
            }
        }
        else if(diff < 0 && size > 0) {
            for(int i = startIndex; i < size - 1; i++) {
                data[i] = data[i - displacement];
            }
            size += diff;
        }
    }

    //insert an element at the index, shifting everything after right.
    public void add(int index, String element) throws IndexOutOfBoundsException {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("SuperArray.add expected an index from [0, size(" + size + ")], instead got " + index);
        }
        if(index == size) {
            if(size >= data.length) {grow();}
            data[size] = element;
            size++;
        } else {
            shift(index, 1);
            data[index] = element;
        }
    }

    //removes the element at the specified index, shifts everything to the left
    public String remove(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("SuperArray.remove expected an index from [0, size), instead got " + index);
        }
        String removed = get(index);
        shift(index, -1);
        return removed;
    }

    //trims the capacity of this SuperArray to the current size
    public void trimToSize() {
        String[] trimmed = new String[size];
        for(int i = 0; i < size; i++) {
            trimmed[i] = data[i];
        }
        data = trimmed;
    }

    public Iterator<String> iterator() {
	return new SuperArrayIterator(this);
    }

    public static String join(String[] array) {
        String s = "[";
        for(int i = 0; i < array.length; i++) {
            s += (array[i] + ", ");
        }
        return s.substring(0, s.length() - 2) + "]";
    }

    public static void main(String[] args) {
        SuperArray a = new SuperArray();
        System.out.println(a);
        a.add("");
        System.out.println(a);
        for(char i = 'A'; i < 'a'; i++) {
            a.add("" + i);
        }
        System.out.println(a.toStringDebug());
        System.out.println("size:      " + a.getSize());
        System.out.println("empty:     " + a.isEmpty());
        System.out.println("set 0:     " + a.set(0, "!"));
        System.out.println("set last:  " + a.set(a.getSize()-1, "!"));
        System.out.println(a);
        System.out.println("~~~~~~~~");
        SuperArray b = new SuperArray();
        b.add("K");
        b.add("DW");
        b.add("Brown");
        b.add("Holmes");
        b.add("Genkina");
        System.out.println(b);
        b.add(0, "Khevelev");
        System.out.println(b);
        b.add(3, "Khevelev");
        System.out.println(b);
        b.add(b.getSize(), "Khevelev");
        System.out.println(b);
        System.out.println("firstKev:" + b.indexOf("Khevelev") + "  lastKev:" + b.lastIndexOf("Khevelev"));
        b.remove(0);
        System.out.println(b);
        System.out.println("firstKev:" + b.indexOf("Khevelev") + "  lastKev:" + b.lastIndexOf("Khevelev"));
        b.remove(b.getSize() - 1);
        System.out.println(b);
        System.out.println("firstKev:" + b.indexOf("Khevelev") + "  lastKev:" + b.lastIndexOf("Khevelev"));
        b.remove(b.indexOf("Khevelev"));
        System.out.println(b);
        System.out.println("firstKev:" + b.indexOf("Khevelev") + "  lastKev:" + b.lastIndexOf("Khevelev"));
        /*b.add(-1, "A");
        b.add(-100, "B");
        b.add(b.getSize(), "");
        b.add(2 * b.getSize(), 5);*/
        //b.set(-1, 5);
        //SuperArray err = new SuperArray(-1);
        System.out.println("~~~~~~~~");
        SuperArray c = new SuperArray(32);
        c.add("Derek Tran");
        c.add("Derk Tran");
        c.add("Drk Tran");
        c.add("DTran");
        System.out.println(c);
        //System.out.println();
    }
}
