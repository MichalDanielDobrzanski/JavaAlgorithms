package org.algorithms;

import org.algorithms.input.ArrayWithIntData;

public class BinarySearch extends BaseAlgorithm<ArrayWithIntData> {

    public BinarySearch(ArrayWithIntData arrayWithInt) {
        super(arrayWithInt);
    }

    @Override
    public void execute() {
        int[] data = this.input.first;
        int number = this.input.second;
//        int index = binarySearch(data, number);
        int index = binarySearchBroken(data, number);
        System.out.println("Resulting index is: " + index);
    }

    public static int binarySearchBroken(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private int binarySearch(int[] data, int number) {
        if (data.length == 0) return -1;
        int l = 0;
        int r = data.length - 1;
        return binarySearchRec(data, l, r, number);
    }

    private int binarySearchRec(int[] data, int l, int r, int number) {
        if (l == r) return l;
        int mid = (r + l) / 2;
        if (number == data[mid]) return mid;
        else if (number > data[mid]) return binarySearchRec(data, mid + 1, r, number);
        else return binarySearchRec(data, l, mid, number);
    }

    @Override
    public String describe() {
        return "Binary Search";
    }
}
