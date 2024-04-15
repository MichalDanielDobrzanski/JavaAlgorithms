import input.ArrayWithIntData;
import input.DoubleArrayData;
import input.StringData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<BaseAlgorithm> algos = new ArrayList<>(List.of(
                new MedianOfSortedArrays(
                        new DoubleArrayData(
                                new int[][]{
                                        new int[]{1, 2},
                                        new int[]{3, 4}
                                }
                        )
                ),
                new LongestPalindrome(new StringData("babac")),
                new GasStations(new StringData(""))
        ));
        algos.addAll(
                IntStream.range(7, 9)
                        .boxed()
                        .map((number) -> new BinarySearch(new ArrayWithIntData(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, number + 1)))
                        .toList()
        );
        algos.addAll(List.of(
                        new MaxTimeForTickets(new StringData("")),
                        new ThreeSum(new StringData("")),
                        new Converter(new StringData(""))
                )
        );

        algos.addAll(
                List.of(
                        new ArrayWithIntData(new int[]{2, 4, 5}, 7),
                        new ArrayWithIntData(new int[]{1, 4, 5}, 11)
                ).stream().map(CoinChange::new).toList()
        );

        algos.addAll(
                List.of(
                        new ArrayWithIntData(new int[]{4, 2, 2, 7, 8, 1, 2, 8, 1, 0}, 8)
                ).stream().map(SmallestSubarrayGreaterOrEqualThan::new).toList()
        );

        for (BaseAlgorithm algo : algos) {
            System.out.println(algo.describeWithInput() + ":");
            algo.execute();
            System.out.println("\n");
        }
    }
}