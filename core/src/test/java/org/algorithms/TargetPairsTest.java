package org.algorithms;

import org.junit.jupiter.api.Test;

import java.util.List;

class TargetPairsTest {

    @Test
    void shouldSolveInitialCase() {
        int[] input = new int[]{2, 3, 4, 1, 1, 3};
        int target = 4;

        List<int[]> result = new TargetPairs().solve(input, target);

        assert (result.size() == 4); // to satify i < j
    }

    @Test
    void shouldSolveInitialCaseForSecondSolution() {
        int[] input = new int[]{2, 3, 4, 1, 1, 3};
        int target = 4;

        List<int[]> result = new TargetPairs().solve2(input, target);

        assert (result.size() == 4);
    }
}