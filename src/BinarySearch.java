import input.ArrayWithIntData;

public class BinarySearch extends BaseAlgorithm<ArrayWithIntData> {

    BinarySearch(ArrayWithIntData arrayWithInt) {
        super(arrayWithInt);
    }

    @Override
    void execute() {
        int[] data = this.input.array;
        int number = this.input.number;
        int index = binarySearch(data, number);
        System.out.println("Resulting index is: " + index);
    }

    private int binarySearch(int[] data, int number) {
        if (data.length == 0) return -1;
        int l = 0;
        int r = data.length - 1;
        return binarySearchRec(data, l, r, number);
    }

    private int binarySearchRec(int[] data, int l, int r, int number) {
        if (l == r) return l;
        int mid = (r + l) / 2;
        if (number == data[mid]) return mid;
        else if (number > data[mid]) return binarySearchRec(data, mid + 1, r, number);
        else return binarySearchRec(data, l, mid, number);
    }

    @Override
    public String describe() {
        return "Binary Search";
    }
}
