package org.algorithms;

import java.util.*;

public class TargetPairs {

    /**
     * Write a function (in the language of your choice) that, given an array of integers and a target integer,
     * returns all pairs of indices (i, j) such that arr[i] + arr[j] = target.
     * <p>
     * You can assume i < j and no pair should repeat.
     * <p>
     * Aim for better than O(n²) if possible.
     *
     * @param arr    array
     * @param target target number
     * @return list of pairs int the form of 2-element array
     */
    public List<int[]> solve(int[] arr, int target) {
        // T: O(n), S: O(n)
        // all pairs -> need to have different indices
        // handle dupl
        // [2,3,4,1,1,3] t=4
        // pairs: 3,1(1,3) 3,1(1,4) 1,3(3,5) 1,3(4,5)
        // map:
        // 1: [3,4]
        // 3: [1,5]

        Map<Integer, List<Integer>> numberToIndices = new HashMap<>();
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            numberToIndices.computeIfAbsent(arr[i], k -> new LinkedList<>()).add(i);
            nums.add(arr[i]);
        }

        // reduced two-sum to target target:
        // a+b=t, a=t-b, b - in the seen arr
        List<int[]> result = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int counterpart = target - num;
            if (seen.contains(counterpart)) {
                // make res(pairs)
                List<Integer> left = numberToIndices.get(num);
                List<Integer> right = numberToIndices.get(counterpart);

                for (int l : left) {
                    for (int r : right) {
                        result.add(new int[]{Math.min(l, r), Math.max(l, r)});
                    }
                }
            }
            seen.add(num);
        }
        return result;
    }

    public List<int[]> solve2(int[] arr, int target) {
        // all pairs -> need to have different indices
        // handle dupl
        // [2,3,4,1,1,3] t=4
        // pairs: 3,1(1,3) 3,1(1,4) 1,3(3,5) 1,3(4,5)

        Map<Integer, List<Integer>> numberToIndices = new HashMap<>();
        Set<Integer> deduplicated = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            numberToIndices.computeIfAbsent(arr[i], k -> new LinkedList<>()).add(i);
            deduplicated.add(arr[i]);
        }

        int[] nums = deduplicated.stream().mapToInt(i -> i).toArray();
        Arrays.sort(nums);

        int l = 0, r = nums.length - 1;

        List<int[]> result = new LinkedList<>();
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum == target) {
                // make res(pairs)
                List<Integer> left = numberToIndices.get(nums[l]);
                List<Integer> right = numberToIndices.get(nums[r]);

                for (int li : left) {
                    for (int ri : right) {
                        result.add(new int[]{Math.min(li, ri), Math.max(li, ri)});
                    }
                }
                l++;
                r--;
            } else if (sum > 0) {
                r--;
            } else {
                l++;
            }
        }
        return result;
    }
}
