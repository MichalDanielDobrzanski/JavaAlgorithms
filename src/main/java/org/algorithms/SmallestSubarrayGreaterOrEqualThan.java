import input.ArrayWithIntData;
import input.base.BaseAlgorithm;

public class SmallestSubarrayGreaterOrEqualThan extends BaseAlgorithm<ArrayWithIntData> {
    SmallestSubarrayGreaterOrEqualThan(ArrayWithIntData arrayWithIntData) {
        super(arrayWithIntData);
    }

    @Override
    public void execute() {
        int[] arr = input.first;
        int sum = input.second;
        int result = solve(arr, sum);
        System.out.println("Result is: " + result);
    }

    private int solve(int[] arr, int sum) {
        // {4, 2, 2, 7, 8, 1, 2, 8, 1, 0}, >= 8

        int minimalLength = Integer.MAX_VALUE;
        int sumSoFar = 0;
        int l = 0;

        for (int i = 0; i < arr.length; i++) {
            sumSoFar += arr[i];
            while (sumSoFar >= sum) {
                if (i == l) return 1;
                minimalLength = Math.min(minimalLength, i - l + 1);
                sumSoFar -= arr[l];
                l++;
            }
        }
        return minimalLength;
    }

    @Override
    protected String describe() {
        return "Smallest subarray length that is greater or equal than " + input.second;
    }
}
