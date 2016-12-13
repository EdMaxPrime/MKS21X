public class Sorts {
    public static String name() {
	return "06.Zlotskiy.Max";
    }

    /** 
     Selection sort of an int array. 
     *Upon completion, the elements of the array will be in increasing order.
     *@param data  the elements to be sorted.
     */
    public static void selectionSort(int[] data) {
	int index = 0;
	while(index < data.length - 1) {
	    //create range of array
	    //find index of smallest
	    //store THIS in temp
	    //swap THIS with the aforementioned index
	    //increase counter
	}
    }

    /**
       Find smallest element index
       @return the index of the smallest integer
     */
    private static int min(int[] array) {
	return min(array, 0);
    }

    private static int min(int[] array, int start) {
	int smallest = start;
	for(int i = start; i < array.length; i++) {
	    if(array[i] < array[smallest]) {
		smallest = i;
	    }
	}
	return smallest;
    }

    private static String arr2str(int[] array) {
	String str = "";
	for(int i : array) {
	    str += (i + ", ");
	}
	str = str.substring(0, str.length()-2);
	return "[" + str + "]";
    }
    
    public static void main(String[] args) {
	int[] a = {5, 6, 7, 4};
	System.out.println("a: " + arr2str(a));
	System.out.println("Index of smallest: " + min(a));
	System.out.println("Min2: " + min(a, 2));
    }
}
