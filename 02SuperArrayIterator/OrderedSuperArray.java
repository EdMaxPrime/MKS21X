public class OrderedSuperArray extends SuperArray {
    public OrderedSuperArray() {super();}
    public OrderedSuperArray(int capacity) {super(capacity);}
    public OrderedSuperArray(String[] array) {
        super(array.length);
        for(int i = 0; i < array.length; i++) {
            this.add(array[i]);
        }
    }

    public void add(int index, String element) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("OrderedSuperArray does not support adding to a specific index.");
    }
    
    public boolean add(String element) {
        super.add(findInsertIndex(element), element);
        return true;
    }

    /*private int findInsertIndex(int element) {
        for(int i = 0; i < getSize(); i++) {
            if(get(i) > element) {return i;}
        }
        return getSize();
    }*/
    //wrapper function for binary search
    private int findInsertIndex(String element) {
        return binarySearch(toArray(), element);
    }

    //returns the index where the first occurence of
    //value should be. No guarantee that value is in
    //the array. Returns [0, length]
    private int binarySearch(String[] array, String value) {
        int lower = 0, upper = array.length;
        while(lower < upper) {
            int compareIndex;
            if( (upper - lower) % 2 == 0) {
                compareIndex = (upper - lower) / 2 - 1 + lower;
            } else {
                compareIndex = (upper - lower) / 2 + lower;
            }
            //System.out.println("Search: "+value+"\tin: "+SuperArray.join(array)+"\nlower: "+lower+"\tupper: "+upper+"\nmiddle: "+compareIndex+" = "+array[compareIndex]);
            if(value.compareTo(array[compareIndex]) < 0) {
                upper = compareIndex;
            }
            else if(value.equals(array[compareIndex])) {
                return compareIndex;
            }
            else {
                lower = compareIndex + 1;
            }
        }
        return upper;
    }

    public int indexOf(String element) {
        int index = binarySearch(toArray(), element);
        if(index < getSize() && get(index).equals(element)) {
            while(index > 0 && get(index-1).equals(element)) {
                index--;
            }
            return index;
        }
        return -1;
    }

    public int lastIndexOf(String element) {
        int index = binarySearch(toArray(), element);
        if(index < getSize() && get(index).equals(element)) {
            while(index < getSize() && get(index).equals(element)) {
                index++;
            }
            return index - 1;
        }
        return -1;
    }

    public String set(int index, String element) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("OrderedSuperArray does not support setting a specific index");
    }
    
    public static void main(String[] args) {
        OrderedSuperArray a = new OrderedSuperArray();
        System.out.println(a);
        //a.set(1, 5);
        //a.add(1, 5);
        a.add("A");
        a.add("a");
        a.add("AB");
        a.add("ABB");
        System.out.println(a);
        System.out.println("~~~~~~~~");
        String[] b_array = new String[] {"1", "50", "2", "54", "2", "51", "3", "52", "4", "53", "5"};
        OrderedSuperArray b = new OrderedSuperArray(b_array);
        System.out.println(b);
        System.out.println("indexOf(6) ->" + b.indexOf("6"));
        System.out.println("indexOf(2) ->" + b.indexOf("2"));
        System.out.println("indexOf(1) ->" + b.indexOf("1"));
        System.out.println("indexOf(50) ->" + b.indexOf("50"));
        System.out.println("lastIndexOf(1) ->" + b.lastIndexOf("1"));
        System.out.println("lastIndexOf(2) ->" + b.lastIndexOf("2"));
        System.out.println("lastIndexOf(4) ->" + b.lastIndexOf("4"));
        System.out.println("~~~~~~~~");
        OrderedSuperArray c = new OrderedSuperArray(50);
        char c_char = '!';
        while(c_char < '~') {
            c.add(c_char + "" + (char)(c_char + 2));
            c_char += 3;
        }
        System.out.println(c + " size:" + c.getSize());
    }
}
