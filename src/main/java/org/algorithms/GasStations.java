import input.StringData;
import input.base.BaseAlgorithm;

/**
 * https://leetcode.com/problems/gas-station/
 */
public class GasStations extends BaseAlgorithm<StringData> {

    GasStations(StringData stringData) {
        super(stringData);
    }

    @Override
    public void execute() {
//        int[] gas = new int[]{1, 2, 3, 4, 5};
//        int[] cost = new int[]{3, 4, 5, 1, 2};

        int[] gas = new int[]{2, 3, 4};
        int[] cost = new int[]{3, 4, 3};
        int canComplete = canCompleteCircuit(gas, cost);
        System.out.println("canComplete: " + canComplete);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        //             x
        // gas  [1,2,3,4,5]
        // cost [3,4,5,1,2]

        // t   c   g
        // 4 - 1 + 5 = 8
        // 8 - 2 + 1 = 7
        // 7 - 3 + 2 = 6
        // 6 - 4 + 3 = 5
        // 5 - 5 = 0

        // 2,3,4
        // 3,4,3
        for (int pos = 0; pos < gas.length; pos++) {
            int station = 0;
            int tank = 0;
            while (station < gas.length) {
                int nextIndex = (pos + station) % gas.length;
                tank += gas[nextIndex] - cost[nextIndex];
                if (tank < 0) break;
                station++;
            }
            if (station == gas.length) {
                return pos;
            }
        }
        return -1;
    }

    @Override
    protected String describe() {
        return "Gas stations";
    }
}
