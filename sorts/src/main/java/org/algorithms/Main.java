package org.algorithms;

import java.util.Arrays;

/**
 * Testing different sort algorithms.
 */
public class Main {

    private static void mergeSort(int[] array) {
        merge(array, 0, array.length - 1);
    }

    private static void merge(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (r + l) / 2;
        merge(array, l, mid);
        merge(array, mid + 1, r);
        // sort l-r
        mergeArray(array, l, r, mid);
    }

    // merge two sorted halves, like:
    // [7,8,9] and [3,6,10,11]
    // [l,..,mid] and [mid+1,...,r]
    private static void mergeArray(int[] array, int l, int r, int mid) {
        int[] result = new int[r - l + 1];
        int li = l, ri = mid + 1, k = 0;
        while (li <= mid && ri <= r) {
            // used <= for stable sorting
            if (array[li] <= array[ri]) {
                result[k] = array[li];
                li++;
            } else {
                result[k] = array[ri];
                ri++;
            }
            k++;
        }
        while (li <= mid) {
            result[k] = array[li];
            li++;
            k++;
        }

        while (ri <= r) {
            result[k] = array[ri];
            ri++;
            k++;
        }
        for (int i = 0; i < result.length; i++) {
            array[l + i] = result[i];
        }
    }

    // quick sort
    private static void quickSort(int[] array) {
        qSort(array, 0, array.length - 1);
    }

    private static void qSort(int[] array, int l, int r) {
        if (l >= r) return;
        // partition
        int pIndex = partition(array, l, r);
        qSort(array, l, pIndex - 1);
        qSort(array, pIndex + 1, r);
    }

    private static int partition(int[] array, int l, int r) {
        int pivot = array[l];
        int pI = l;
        for (int i = l + 1; i <= r; i++) {
            if (array[i] <= pivot) {
                pI++;
                swap(array, pI, i);
            }
        }
        swap(array, l, pI);
        return pI;
    }

    private static void swap(int[] array, int s, int t) {
        int temp = array[s];
        array[s] = array[t];
        array[t] = temp;
    }


    public static void main(String[] args) {
        int[] array = new int[]{4, 3, 1, 9, 5, 7, 2};
        System.out.println("Merge sort of: " + Arrays.toString(array));
        mergeSort(array);
        System.out.println("Output: " + Arrays.toString(array));

        int[] array2 = new int[]{4, 3, 1, 9, 5, 7, 2};
        System.out.println("Quick sort of: " + Arrays.toString(array2));
        quickSort(array2);
        System.out.println("Output of quick sort: " + Arrays.toString(array2));
    }
}