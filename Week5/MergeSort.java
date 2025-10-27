package Labs.Week5;

public class MergeSort {
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }
    
    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
    
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int leftIndex = 0, rightIndex = 0, mergeIndex = left;
        
        while (leftIndex < n1 && rightIndex < n2) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                array[mergeIndex++] = leftArray[leftIndex++];
            } else {
                array[mergeIndex++] = rightArray[rightIndex++];
            }
        }
        
        while (leftIndex < n1) {
            array[mergeIndex++] = leftArray[leftIndex++];
        }
        
        while (rightIndex < n2) {
            array[mergeIndex++] = rightArray[rightIndex++];
        }
    }
    
    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original array: " + java.util.Arrays.toString(array));
        
        mergeSort(array, 0, array.length - 1);
        System.out.println("Sorted array: " + java.util.Arrays.toString(array));
    }
}
