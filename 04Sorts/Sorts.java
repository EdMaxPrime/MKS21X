import java.util.Random;
import java.util.Arrays;
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
	if(array.length == 0) {return "[]";}
	String str = "";
	for(int i : array) {
	    str += (i + ", ");
	}
	str = str.substring(0, str.length()-2);
	return "[" + str + "]";
    }

    private static interface Sorter {
	public void sort(int[] array);
    }

    private static void testSorter(Sorter s, int seed) {
	Random rng = new Random(seed);
	int[] empty = {};
	s.sort(empty);
	System.out.println("Empty array: " + arr2str(empty));
	for(int i = 0; i < 5; i++) {
	    int len = 3 + rng.nextInt(13);
	    int[] unsorted = new int[len];
	    for(int j = 0; j < unsorted.length; j++) {unsorted[j] = -100 + rng.nextInt(200);}
	    System.out.println("===Test #" + (i + 1));
	    System.out.println("Before: " + arr2str(unsorted));
	    s.sort(unsorted);
	    System.out.println("After: " + arr2str(unsorted));
	}
	int[] times = new int[10];
	int[] yuuge = new int[1];
	for(int t = 0; t < times.length; t++) {
	    yuuge = new int[5000];
	    for(int j = 0; j < yuuge.length; j++) {yuuge[j] = -1000 + rng.nextInt(2000);}
	    long time = System.currentTimeMillis();
	    s.sort(yuuge);
	    times[t] = (int)(System.currentTimeMillis() - time);
	}
	s.sort(times);
	System.out.println("Times("+yuuge.length+"): " + arr2str(times) + " ms");
    }
    
    public static void main(String[] args) {
	/*int[] a = {5, 6, 7, 4};
	System.out.println("a: " + arr2str(a));
	System.out.println("Index of smallest: " + min(a));
	System.out.println("Min2: " + min(a, 2));
	selectionSort(a);
	System.out.println("Sorted: " + arr2str(a));*/
	Random rng = new Random();
	int seed = rng.nextInt();
	System.out.println("##### SELECTION SORT #####");
	testSorter(new Sorter() {
		public void sort(int[] array) {
		    selectionSort(array);
		}
	    }, seed);
	System.out.println("##### INSERTION SORT #####");
	testSorter(new Sorter() {
		public void sort(int[] array) {
		    insertionSort(array);
		}
	    }, seed);
	System.out.println("##### BUBBLE SORT #####");
	testSorter(new Sorter() {
		public void sort(int[] array) {
		    bubbleSort(array);
		}
	    }, seed);
    }
}
