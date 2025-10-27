package Labs.Week5;

public class InsertionSort {
    public static void insertionSort(int[] array, int n) {
        for (int index = 1; index < n; index++) {
            int key = array[index];
            int position = index - 1;

            while (position >= 0 && array[position] > key) {
                array[position + 1] = array[position];
                position--;
            }
            array[position + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 8, 1, 3};
        int n = array.length;

        insertionSort(array, n);

        System.out.println("Sorted array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
    
}
