package com.algo.string.kmp;

/**
 * Do pattern matching using KMP algorithm
 *
 * Runtime complexity - O(m + n) where m is length of text and n is length of pattern
 * Space complexity - O(n)
 */
public class KMP {

    /**
             i   j
          abccababac
          0123456789

    lps = 0000121210
    */
    private int[] buildPattern(String pattern) {
        int[] lps = new int[pattern.length()];
        char[] chars = pattern.toCharArray();
        for(int i = 0, j = 1; j < chars.length;) {
            if(chars[i] == chars[j]) {
                lps[j++] = i++ + 1;
            } else {
                if(i != 0) {
                    i = lps[i - 1];
                } else {
                    j++;
                }
            }
        }
        return lps;
    }

    public boolean searchPattern(String str, String pattern) {
        int[] lps = buildPattern(pattern);
        int i = 0, j = 0;
        while (i < str.length() && j < pattern.length()) {
            if(str.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if(j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return j == pattern.length();
    }

}
