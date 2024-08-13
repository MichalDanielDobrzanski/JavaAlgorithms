package org.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * <a href="https://leetcode.com/explore/featured/card/recursion-i/255/recursion-memoization/1495/">...</a>
 */
public class MemoizationDecorator extends BaseCustomAlgorithm {
    @Override
    public void execute() {
        int n = 44;

        var withMemoization = new FibAlgorithmWithMemoization();
        System.out.println("Value for the one with memoization is: " + withMemoization.execute(n));

        var normal = new AbstractedFibAlgorithm((argWithCaller) -> argWithCaller.fibAlgorithm.execute(argWithCaller.n));
        System.out.println("Value for normal is: " + normal.execute(n));
    }

    @Override
    protected String describe() {
        return "Using memoization for Fibonnaci sequence as a decorator pattern";
    }


    private interface FibAlgorithm {
        int execute(int n);
    }

    private record ArgWithCaller(
            FibAlgorithm fibAlgorithm,
            int n
    ) {
    }

    private record AbstractedFibAlgorithm(Function<ArgWithCaller, Integer> function) implements FibAlgorithm {

        @Override
        public int execute(int n) {
            if (n < 2) return n;
            return function.apply(new ArgWithCaller(this, n - 1)) + function.apply(new ArgWithCaller(this, n - 2));
        }
    }

    /**
     * Decorator
     */
    private static class FibAlgorithmWithMemoization implements FibAlgorithm {

        private final AbstractedFibAlgorithm fibAlgorithm;
        private final Map<Integer, Integer> cache = new HashMap<>();

        public FibAlgorithmWithMemoization() {
            this.fibAlgorithm = new AbstractedFibAlgorithm((argWithCaller) -> {
                int n = argWithCaller.n;
                if (cache.containsKey(n)) return cache.get(n);
                int result = argWithCaller.fibAlgorithm.execute(n);
                cache.put(n, result);
                return result;
            });
        }

        @Override
        public int execute(int n) {
            return fibAlgorithm.execute(n);
        }
    }
}
