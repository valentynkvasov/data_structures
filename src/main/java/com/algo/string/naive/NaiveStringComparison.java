package com.algo.string.naive;

/**
 * Do pattern matching using naive algorithm
 *
 * Runtime complexity - O(mn) where m is length of text and n is length of pattern
 * Space complexity - O(1)
 */
public class NaiveStringComparison {

    public boolean searchPattern(String str, String pattern) {
        if(pattern.length() == 0) {
            return true;
        }
        if(str.length() == 0 || str.length() < pattern.length()) {
            return false;
        }
        for(int i = 0, j = 0; i < str.length(); j = 0) {
            int save = i;
            while(i < str.length() && j < pattern.length() && (str.charAt(i) == pattern.charAt(j))) {
                i++;
                j++;
            }
            if(j == pattern.length()) {
                return true;
            }
            i = save + 1;
        }
        return false;
    }

}
