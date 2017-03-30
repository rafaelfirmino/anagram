package com.jumia.porto.tech.domain;

import java.util.Arrays;

/**
 * @author rgfirmino
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 3/29/17 7:25 PM
 */
public final class Anagram {

    private Anagram() {
    }

    public static boolean isAnagram(final String firstComparison, final String secondComparison) {
        if (firstComparison == null || secondComparison == null) {
            return false;
        }

        final char[] firstArray = firstComparison.toUpperCase().toCharArray();
        final char[] secondArray = secondComparison.toUpperCase().toCharArray();

        if (firstArray.length != secondArray.length) {
            return false;
        }

        Arrays.sort(firstArray);
        Arrays.sort(secondArray);

        return Arrays.toString(firstArray).equals(Arrays.toString(secondArray));
    }
}
