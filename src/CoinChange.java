import input.ArrayWithIntData;

import java.util.Arrays;

public class CoinChange extends BaseAlgorithm<ArrayWithIntData> {
    CoinChange(ArrayWithIntData arrayWithIntData) {
        super(arrayWithIntData);
    }

    @Override
    void execute() {
        int[] coins = input.first;
        int amount = input.second;
        int result = coinChange(coins, amount);
        System.out.println("Min coins number is: " + result);
    }

    public int coinChange(int[] coins, int amount) {
        // top-down (try breaking 11)
        // bottom up (try building up to 11)

        //    2 4 5
        // 1| 0 0 0
        // 2| 1 1 1
        // 3| 0 0 0
        // 4| 2 1 1
        // 5| 0 0 1
        // 6| 3 2 2
        // 7| 0 0 2

        Arrays.sort(coins);
        int[][] changes = new int[amount + 1][coins.length + 1];

        for (int a = 1; a <= amount; a++) {
            changes[a][0] = -1;
            for (int j = 1; j <= coins.length; j++) {
                if (a < coins[0]) changes[a][j] = -1;
            }
        }


        for (int a = coins[0]; a <= amount; a++) {
            for (int j = 1; j <= coins.length; j++) {
                int coin = coins[j - 1];

                int withoutMe = a - coin < 0 ? -1 : changes[a - coin][j];
                int previous = changes[a][j - 1];
                if (withoutMe == -1 && previous == -1) {
                    changes[a][j] = -1;
                } else if (previous == -1) {
                    changes[a][j] = withoutMe + 1;
                } else if (withoutMe == -1) {
                    changes[a][j] = previous;
                } else {
                    changes[a][j] = Math.min(withoutMe + 1, previous);
                }

            }
        }

        System.out.println(Arrays.deepToString(changes));

        return changes[amount][coins.length];
    }

    @Override
    protected String describe() {
        return "Coin change";
    }
}
