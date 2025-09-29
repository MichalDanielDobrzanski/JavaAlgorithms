package org.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IntegerContainerWithMedian {

    private final List<Integer> nums;

    public IntegerContainerWithMedian() {
        nums = new ArrayList<>();
    }

    private int binarySearch(int[] arr, int value) {
        return binSearchRec(arr, value, 0, arr.length - 1);
    }

    private int binSearchRec(int[] arr, int value, int l, int r) {
        if (l > r) return -1;

        int mid = (l + r) / 2;
        if (value == arr[mid]) return mid;
        else if (value < arr[mid]) return binSearchRec(arr, value, l, mid-1);
        return binSearchRec(arr, value, mid+1, r);
    }


    // O(n) - because of array resize
    public int add(int value) {
        int pos = binarySearch(nums.stream().mapToInt(Integer::intValue).toArray(), value);
        if (pos < 0) pos = -pos - 1;
        nums.add(pos, value);
        return nums.size();
    }

    // O(n) - because of array resize
    public boolean delete(int value) {
        int pos = binarySearch(nums.stream().mapToInt(Integer::intValue).toArray(), value);
        if (pos >= 0) {
            nums.remove(pos);
            return true;
        }
        return false;
    }

    // O(log(n))
    public Optional<Integer> getMedian() {
        if (nums.isEmpty()) return Optional.empty();
        int n = nums.size();
        if (n == 1) return Optional.of(nums.get(0));


        if (n % 2 == 0) {
            return Optional.of(nums.get(n / 2 - 1));
        } else {
            return Optional.of(nums.get(n / 2));
        }
    }
}

