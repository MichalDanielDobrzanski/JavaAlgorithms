package org.algorithms;

import org.algorithms.input.ArrayData;

import java.util.Arrays;

public class QuickSort extends BaseAlgorithm<ArrayData> {
    public QuickSort(ArrayData arrayData) {
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

    /**
     * Lomato partitioning
     */
    private int partition(int[] array, int l, int r) {
        if (l >= r) return l;
        int pivot = array[l];
        int p = l;
        int i = l + 1;
        while (i <= r) {
            if (array[i] < pivot) {
                p++;
                swap(array, i, p);
            }
            i++;
        }
        swap(array, l, p);
        return p;
    }

    private void swap(int[] array, int s, int t) {
        int temp = array[s];
        array[s] = array[t];
        array[t] = temp;
    }

    @Override
    protected String describe() {
        return "Quick sorting of this array: " + input.asText();
    }
}
