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
        int index = binarySearch(data, number);
        System.out.println("Resulting index for recursive is: " + index);
        int indexIter = binarySearchIterative(data, number);
        System.out.println("Resulting index for iterative is: " + indexIter);
    }

    int binarySearch(int[] data, int number) {
        if (data.length == 0) return -1;
        return search(data, number, 0, data.length - 1);
    }

    int search(int[] data, int number, int l, int r) {
        if (l > r) return -1;
        int mid = (l + r) / 2;
        if (data[mid] == number) return mid;
        else if (data[mid] < number) {
            return search(data, number, mid + 1, r);
        } else {
            return search(data, number, l, mid - 1);
        }
    }

    int binarySearchIterative(int[] data, int number) {
        if (data.length == 0) return -1;
        int l = 0;
        int r = data.length - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (data[mid] == number) return mid;
            else if (data[mid] < number) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }

    @Override
    public String describe() {
        return "Binary Search";
    }
}
