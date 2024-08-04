import input.ArrayData;
import input.base.BaseAlgorithm;

import java.util.Arrays;

public class QuickSort extends BaseAlgorithm<ArrayData> {
    QuickSort(ArrayData arrayData) {
        super(arrayData);
    }

    @Override
    public void execute() {
        int[] array = input.data;
        quickSort(array, 0, array.length - 1);
        System.out.println("Sorted: " + Arrays.toString(array));
    }

    private void quickSort(int[] array, int l, int r) {
        if (l >= r) return;

        int pivotIndex = partition(array, l, r);
        quickSort(array, l, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, r);
    }

    // https://www.youtube.com/watch?v=Vtckgz38QHs
    // [ _, 7, 6, 4, 1, 9, 8, 3 ]
    //   ^  ^                 ^
    //   i  j                 p

    // after the end:
    //      i                 p
    //             swap
    //      p                 i
    //
    // This approach is based on assumption that elements
    // smaller than pivot must be moved to left.
    // We skip greater elements, just handling smaller ones.
    // i is initialised to l - 1 (negative index) to handle
    // case of first element being smaller than index
    private int partition(int[] array, int l, int r) {
        int pivot = array[r];
        // to handle first element smaller than pivot
        int i = l - 1;
        for (int j = l; j <= r - 1; j++) {
            if (array[j] < pivot) {
                // if the first is smaller, this will be
                // a no-op in terms of making a swap of
                // array[0] with array[0]
                i++;
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }
        // we are at the end. The j index has arrived t the pivot.
        // we handled all smaller elements and i points to the smaller
        // than pivot.

        // need to increment smaller index to perform final swap
        // with pivot
        i++;

        // swapping with pivot (pointed by r)
        int t = array[i];
        array[i] = array[r];
        array[r] = t;

        // returning the index of a pivot for further recurrence
        return i;
    }

    @Override
    protected String describe() {
        return "Quick sorting of this array: " + input.asText();
    }
}
