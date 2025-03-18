package org.algorithms;

import org.algorithms.input.ArrayData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

public class EfficientRemovalOps extends BaseAlgorithm<ArrayData> {
    public EfficientRemovalOps(ArrayData arrayData) {
        super(arrayData);
    }

    // [1, 2, 3, -1, -1]
    // 1
    // 1,2
    // 1,2,3
    // 1,3
    // 1
    @Override
    public void execute() {
        int[] numbers = input.data;
        int[] result = solve(numbers);
        System.out.println("Result: " + Arrays.toString(result));
    }


    // O(n * log(n))
    // Input: [3, 5, -1, 7, -2, 4, 0, -3]:
    // Output: [5, 4]
    private int[] solve(int[] numbers) {
        // We'll store elements in a TreeMap, keyed by an increasing integer.
        // The key determines insertion order, but we do not rely on it for 1-based indexing.
        // Instead, we'll remove by 0-based index from the current "sorted keys".

        TreeMap<Integer, Integer> map = new TreeMap<>();

        // We'll count insertions starting at 0.
        // Each positive number gets the current insertCount, then increment it.
        int insertCount = 0;

        for (int cmd : numbers) {
            if (cmd == 0) {
                // 0 => do nothing
                continue;

            } else if (cmd > 0) {
                // Positive => put this element at the next available "key"
                map.put(insertCount, cmd);
                insertCount++;

            } else {
                // Negative => remove the element at 0-based index = -cmd
                int removeIndex = -cmd; // 0-based index in the *current* list

                if (removeIndex < map.size()) {
                    // Convert keySet to array, find the key at "removeIndex", remove it
                    Integer keyToRemove = (Integer) map.keySet().toArray()[removeIndex];
                    map.remove(keyToRemove);
                }
            }
        }

        // Step 2: Extract values in order
        return map.values().stream().mapToInt(e -> e).toArray();  // O(n)
    }

    @Override
    protected String describe() {
        return """
                There is a coding task: there is a list of numbers.
                0 - does not do anything
                positive number - pushes element to the output list
                negative number - removes element at index abs(negative number)
                """;
    }
}
