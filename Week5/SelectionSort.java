package Week5;

public class SelectionSort {
    public static void selectionSort(int[] array, int n) {
        for (int index = 0; index < n - 1; index++) {
            int minIndex = index;
            for (int scan = index + 1; scan < n; scan++)
                if (array[scan] < array[minIndex])
                    minIndex = scan;

            int temp = array[minIndex];
            array[minIndex] = array[index];
            array[index] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 8, 1, 3};
        int n = array.length;

        selectionSort(array, n);

        System.out.println("Sorted array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
