import input.StringData;

/**
 * https://leetcode.com/problems/time-needed-to-buy-tickets/
 */
public class MaxTimeForTickets extends BaseAlgorithm<StringData> {


    MaxTimeForTickets(StringData stringData) {
        super(stringData);
    }

    @Override
    void execute() {
        int[] tickets = new int[]{2, 3, 2};
        int k = 0;

        int timeToBuy = timeRequiredToBuyBruteForce(tickets, k);
        System.out.println("timeToBuy: " + timeToBuy);
    }

    /**
     * O(n^2)
     */
    private int timeRequiredToBuyBruteForce(int[] tickets, int k) {
        // k = 2
        // 2,3,2
        // 1,2,1,
        // 0,1,0

        // k = 0
        // 5,1,1,1
        // 4,0,0,0
        // 3,0,0,0
        // 2,0,0,0
        // 1,0,0,0
        // 0,0,0,0

        // k = 1
        // 5,1,1,1
        // 4,0,0,0

        int timeToBuy = 0;
        while (tickets[k] > 0) {
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] == 0) continue;
                tickets[i]--;
                timeToBuy++;
                if (i == k && tickets[k] == 0) return timeToBuy;
            }
        }
        return timeToBuy;
    }

    

    @Override
    protected String describe() {
        return "Max Time for tickets: ";
    }
}
