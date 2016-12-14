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
	//while THE_WALL < len-1
	//    take WALL+1
	//    if its < wall {loop[0, wall): find insert index, insert}
	//    wall++
    }

    private static void shift(int[] array, int start, int end) {
	int temp = array[start];
	for(int i = start+1; i < end; i++) {
	    if(i == start + 1) {
		array[i] = array[i-1];
	    } else {
		array[i] = temp;
	    }
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
	for(int i = 0; i < 5; i++) {
	    int len = 3 + rng.nextInt(8);
	    int[] bc = new int[len];
	    while(len > 0) {bc[len-1] = rng.nextInt(50); len--;}
	    System.out.println("===Test #" + i);
	    System.out.println("Before: " + arr2str(bc));
	    selectionSort(bc);
	    System.out.println("After: " + arr2str(bc));
	}
	int[] d = {4, 5, 6, 7, 7};
	shift(d, 0, 5);
	System.out.println(arr2str(d));
    }
}
