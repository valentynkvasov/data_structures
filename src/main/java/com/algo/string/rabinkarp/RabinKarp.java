package com.algo.string.rabinkarp;

/**
 * Do pattern matching using Rabin Karp algorithm
 *
 * Runtime complexity - O(mn) where m is length of text and n is length of pattern
 * Space complexity - O(1)
 */
public class RabinKarp {

    private final int prime = 3;

    public boolean searchPattern(String str, String pattern) {
        if(pattern.length() > str.length()) {
            return false;
        }
        long strHash = createHash(str, pattern.length());
        long patternHash = createHash(pattern, pattern.length());
        int len = str.length() - pattern.length() + 1;
        for (int i = 1; i <= len; i++) {
            if(strHash == patternHash && isEqual(str, i - 1, i + pattern.length() - 1, pattern, 0)) {
                return true;
            }
            if(i < len) {
                strHash = recreateHash(str, i - 1, i + pattern.length() - 1, strHash, pattern.length());
            }
        }
        return false;
    }

    /**
      abdcf - {'a':1, 'b':2, 'd':4, 'c':3, 'f':6}
      createHash("abcd") = (1 * 3^0) + (2 * 3^1) + (4 * 3^2) + (3 * 3^3) = 3 + 6 + 36 + 81 = 126
     */
    private long createHash(String str, int end) {
        long hash = 0;
        for(int i = 0; i < end; i++) {
            hash += str.charAt(i) * Math.pow(prime, i);
        }
        return hash;
    }

    /**
     * abdcf - {'a':1, 'b':2, 'd':4, 'c':3, 'f':6}
     * createHash("abcd") = 126
     *
     *                                            (1*3^0)+(2*3^1)+(4*3^2)+(3*3^3)
     * recreateHash("bdcf") = 126 - 1 = 125 / 3 = ------------------------------ = (2*3^0)+(4*3^1)+(3*3^2)+(6*3^3) = 203
     *                                                         3
     */
    private long recreateHash(String str, int oldIndex, int newIndex, long oldHash, int len) {
        long newHash = oldHash - str.charAt(oldIndex);
        newHash /= prime;
        newHash += str.charAt(newIndex) * Math.pow(prime, len - 1);
        return newHash;
    }

    private boolean isEqual(String str, int strIndex, int strEndIndex, String pattern, int patternIndex) {
        while (strIndex <= strEndIndex && patternIndex < pattern.length()) {
            if(str.charAt(strIndex++) != pattern.charAt(patternIndex++)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        RabinKarp karp = new RabinKarp();
        karp.searchPattern("aabdcf", "abdcf");
    }
}
