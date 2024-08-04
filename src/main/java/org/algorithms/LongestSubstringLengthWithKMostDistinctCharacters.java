import input.StringWithIntData;
import input.base.BaseAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * K most distinct
 * k = 2, then a possible option is:
 * - AAAAAAAABB
 * k = 1
 * - AAAAAA
 * <p>
 * Ref: https://gist.github.com/Schachte/87d7c0165a584f26b3ad7845f8010387
 */
public class LongestSubstringLengthWithKMostDistinctCharacters extends BaseAlgorithm<StringWithIntData> {
    LongestSubstringLengthWithKMostDistinctCharacters(StringWithIntData stringWithIntData) {
        super(stringWithIntData);
    }

    @Override
    public void execute() {
        String text = input.first;
        int k = input.second;
        int longestLength = solve(text, k);
        System.out.println("longestLength is " + longestLength);
    }

    private int solve(String text, int k) {
        // AAAHHIBC, k=2
        // A *
        // AA *
        // AAA *
        // AAAH *
        // AAAHH *
        // AAAHHI - to remove all before H
        //    HHI *
        //      IB *
        //       BC *

        // { A: 3, H: 2 }

        int longestSubstring = Integer.MIN_VALUE;
        int l = 0;
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (int r = 0; r < text.length(); r++) {
            char curr = text.charAt(r);
            frequencyMap.put(curr, frequencyMap.getOrDefault(curr, 0) + 1);

            while (frequencyMap.size() > k) {
                char left = text.charAt(l);
                frequencyMap.put(left, frequencyMap.getOrDefault(left, 0) - 1);
                if (frequencyMap.get(left) == 0) frequencyMap.remove(left);
                l++;
            }

            longestSubstring = Math.max(longestSubstring, r - l + 1);
        }
        return longestSubstring;
    }

    @Override
    protected String describe() {
        return "Longest Substring Length with " + input.second + " most distinct characters";
    }
}
