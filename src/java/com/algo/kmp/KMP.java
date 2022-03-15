package com.algo.kmp;

/**
 * Do pattern matching using KMP algorithm
 *
 * Runtime complexity - O(m + n) where m is length of text and n is length of pattern
 * Space complexity - O(n)
 */
public class KMP {

    //  i   j
    //abccababac
    //0123456789

    //0000121210
    private static int[] buildPattern(String pattern) {
        int[] kmpArray = new int[pattern.length()];
        char[] chars = pattern.toCharArray();
        for(int i = 0, j = 1; j < chars.length;) {
            if(chars[i] == chars[j]) {
                kmpArray[j++] = i++ + 1;
            } else {
                if(i != 0) {
                    i = kmpArray[i - 1];
                } else {
                    j++;
                }
            }
        }
        return kmpArray;
    }

    private static boolean searchSubstring(String str, String pattern) {
        int[] kmpArray = buildPattern(pattern);
        int i = 0, j = 0;
        while (i < str.length() && j < pattern.length()) {
            if(str.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if(j != 0) {
                    j = kmpArray[j - 1];
                } else {
                    i++;
                }
            }
        }
        return j == pattern.length();
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();
        System.out.println(kmp.searchSubstring("absccaabccabazbac", "abccababac"));
    }
}
