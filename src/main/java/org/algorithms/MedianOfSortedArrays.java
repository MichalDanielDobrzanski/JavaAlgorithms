import input.DoubleArrayData;
import input.base.BaseAlgorithm;

import java.util.List;

public class MedianOfSortedArrays extends BaseAlgorithm<DoubleArrayData> {

    MedianOfSortedArrays(DoubleArrayData input) {
        super(input);
    }

    public double findMedianSortedArrays() {
        int[] nums1 = input.data[0];
        int[] nums2 = input.data[1];
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                merged[k] = nums1[i];
                k++;
                merged[k] = nums2[j];
                k++;
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                merged[k] = nums2[j];
                j++;
                k++;
            } else {
                merged[k] = nums1[i];
                i++;
                k++;
            }
        }
        while (i < nums1.length) {
            merged[k] = nums1[i];
            i++;
            k++;
        }
        while (j < nums2.length) {
            merged[k] = nums2[j];
            j++;
            k++;
        }

        int s = merged.length;
        if (s % 2 == 0) {
            return (merged[s / 2] * 1.0 + merged[s / 2 - 1]) / 2.0;
        } else {
            return merged[s / 2] * 1.0;
        }
    }

    @Override
    public void execute() {
        List<List<int[]>> inputs = List.of(
                List.of(new int[]{1, 2}, new int[]{3, 4}),
                List.of(new int[]{0, 0}, new int[]{0, 0}),
                List.of(new int[]{0, 0, 0, 0, 0}, new int[]{-1, 0, 0, 0, 0, 0, 1})
        );
        for (List<int[]> i : inputs) {
            var median = findMedianSortedArrays();
            System.out.printf("median is " + median);
        }
    }

    @Override
    public String describe() {
        return "Median of sorted arrays";
    }
}
