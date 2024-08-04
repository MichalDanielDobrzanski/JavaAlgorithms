package org.algorithms;

import org.algorithms.input.StringData;

import java.util.*;

/**
 * https://leetcode.com/problems/3sum/submissions/
 */
public class ThreeSum extends BaseAlgorithm<StringData> {
    public ThreeSum(StringData stringData) {
        super(stringData);
    }

    final int[] input = new int[]{-1, 0, 1, 2, -1, -4};

    @Override
    public void execute() {
        var result = threeSumOptimised(input);

        for (List<Integer> list : result) {
            for (Integer item : list) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> threeSumOnSquared(int[] nums) {
        // nums[i] + nums[j] + nums[k] == 0
        // -nums[i] = nums[j] + nums[k]
        // Notice that the solution set must not contain duplicate triplets. -> put sorted ones.
        Set<List<Integer>> results = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            int target = -1 * nums[i];


            Map<Integer, Integer> counterparts = new HashMap<>();
            for (int j = i + 1; j < nums.length; j++) {

                // -1, 0, 1
                // nums[i] = -1
                // nums[j] = 0
                // nums[k] = --1 + 0 = 1
                // {1: 0}

                // if the number is a counterpart
                if (counterparts.containsKey(nums[j])) {
                    List<Integer> r = new ArrayList<>(3);
                    r.add(nums[i]);
                    r.add(nums[j]);
                    r.add(counterparts.get(nums[j]));
                    Collections.sort(r);
                    results.add(r);
                } else {
                    // nums[k] = -nums[i] - nums[j]
                    int counterpart = target - nums[j];
                    counterparts.put(counterpart, nums[j]);
                }
            }
        }
        return new ArrayList<>(results);
    }

    public List<List<Integer>> threeSumOptimised(int[] nums) {
        // [-1,0,1,2,-1,-4]
        // sorted:
        // [-4,-1,-1,0,1,2]
        //      i    j k

        Set<List<Integer>> results = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // optimisation
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    results.add(List.of(nums[i], nums[j], nums[k]));
                }
                if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return new ArrayList<>(results);
    }

    @Override
    protected String describe() {
        return "Three sum " + Arrays.toString(input);
    }
}
