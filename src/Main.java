import java.util.List;

public class Main {
    public static void main(String[] args) {
        var algos = List.of(
                new MedianOfSortedArrays()
        );
        for (BaseAlgorithm algo : algos) {
            algo.execute();
        }
    }
}