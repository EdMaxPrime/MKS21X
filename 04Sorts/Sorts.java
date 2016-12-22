import java.util.Random;
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
	    int smallest = min(data, index);
	    //store THIS in temp
	    int temp = data[index];
	    //swap THIS with the aforementioned index
	    data[index] = data[smallest];
	    data[smallest] = temp;
	    //increase counter
	    index++;
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

    public static void insertionSort(int[] array) {
	//index of THE WALL = 0
	int sorted = 0;
	//while THE_WALL < len-1
	while(sorted < array.length-1) {
	//    take WALL+1
	//    if its < wall {loop[0, wall): find insert index, insert}
	    if(array[sorted+1] < array[sorted]) {
		int insertIndex = 0;
		while(array[sorted+1] > array[insertIndex]) {
		    insertIndex++;
		}
		int temp = array[sorted+1];
		shift(array, insertIndex, sorted+2);
		array[insertIndex] = temp;
	    }
	//    wall++
	    sorted++;
	}
    }

    private static void shift(int[] array, int start, int end) {
	int temp = array[start];
	for(int i = end-1; i > start; i--) {
	    array[i] = array[i - 1];
	}
    }

    public static void bubbleSort(int[] array) {
	//end = len
	int end = array.length;
	//swapped = true
	boolean swapped = true;
	//while end > 0 and swapped
	while(end > 0 && swapped) {
	//    swapped = false
	    swapped = false;
	//    loop[0, end-1)
	    for(int i = 0; i < end - 1; i++) {
	//        if this > next then
		if(array[i] > array[i + 1]) { 
	//            swap()
		    int temp = array[i];
		    array[i] = array[i + 1];
		    array[i + 1] = temp;
	//            swapped = true
		    swapped = true;
		}
	    }
	    //update end
	    end--;
	}
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
	selectionSort(a);
	System.out.println("Sorted: " + arr2str(a));
	Random rng = new Random();
	System.out.println("##### SELECTION SORT #####");
	for(int i = 0; i < 5; i++) { //selection sort
	    int len = 3 + rng.nextInt(8);
	    int[] bc = new int[len];
	    while(len > 0) {bc[len-1] = rng.nextInt(50); len--;}
	    System.out.println("===Test #" + i);
	    System.out.println("Before: " + arr2str(bc));
	    selectionSort(bc);
	    System.out.println("After: " + arr2str(bc));
	}
	System.out.println("##### INSERTION SORT #####");
	for(int i = 0; i < 5; i++) { //insertion sort
	    int len = 3 + rng.nextInt(13);
	    int[] d = new int[len];
	    while(len > 0) {d[len-1] = rng.nextInt(100) - 50; len--;}
	    System.out.println("===Test #" + i);
	    System.out.println("Before: " + arr2str(d));
	    selectionSort(d);
	    System.out.println("After: " + arr2str(d));
	}
	System.out.println("##### BUBBLE SORT #####");
	for(int i = 0; i < 5; i++) { //bubble sort
	    int len = 3 + rng.nextInt(13);
	    int[] d = new int[len];
	    while(len > 0) {d[len-1] = rng.nextInt(100) - 50; len--;}
	    System.out.println("===Test #" + i);
	    System.out.println("Before: " + arr2str(d));
	    bubbleSort(d);
	    System.out.println("After: " + arr2str(d));
	}
    }
}
