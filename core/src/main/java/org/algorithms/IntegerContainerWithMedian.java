package org.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class IntegerContainerWithMedian {

    private final List<Integer> nums;

    public IntegerContainerWithMedian() {
        nums = new ArrayList<>();
    }

    // O(log(n))
    public int add(int value) {
        int pos = Collections.binarySearch(nums, value);
        if (pos < 0) pos = -pos - 1;
        nums.add(pos, value);
        return nums.size();
    }

    // O(log(n))
    public boolean delete(int value) {
        int pos = Collections.binarySearch(nums, value);
        if (pos >= 0) {
            nums.remove(pos);
            return true;
        }
        return false;
    }

    // O(1)
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

