package input;

import java.util.Arrays;
import java.util.List;

public class AdjacencyListData implements Textable {
    public final List<Integer>[] adjacencyList;

    public AdjacencyListData(List<Integer>[] adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    @Override
    public String asText() {
        return Arrays.toString(adjacencyList);
    }
}