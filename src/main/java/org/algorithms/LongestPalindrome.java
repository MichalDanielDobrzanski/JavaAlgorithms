package org.algorithms;

import org.algorithms.input.StringData;

public class LongestPalindrome extends BaseAlgorithm<StringData> {

    public LongestPalindrome(StringData input) {
        super(input);
    }

    @Override
    public void execute() {
        String result = solveBruteForce(input.data);
        System.out.println(result);
    }

    @Override
    public String describe() {
        return "Longest Palindrome problem";
    }

    private String solveBruteForce(String s) {
        return longestPalindrome(s);
    }


    public String longestPalindrome(String s) {
        String longest = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String substring = s.substring(i, j + 1);
                if (isPalindrome(substring) && substring.length() > longest.length()) {
                    longest = substring;
                }
            }
        }
        return longest;
    }

    private boolean isPalindrome(String str) {
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
