package Week5;

public class QuickSort {
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }
    
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swapElements(array, i, j);
            }
        }
        swapElements(array, i + 1, high);
        return (i + 1);
    }
        
    private static void swapElements(int[] array, int firstIndex, int secondIndex) {
        int temporary = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temporary;
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 8, 1, 3, 7};
        int n = array.length;
        quickSort(array, 0, n - 1);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}