package com.jumia.porto.tech.domain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author rgfirmino
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 3/29/17 7:33 PM
 */
public class AnagramTest {

    @Test
    public void trueAnagramTest() {
        final boolean result = Anagram.isAnagram("ABC", "BCA");
        assertTrue(result);
    }

    @Test
    public void trueAnagramCaseSensitiveTest() {
        final boolean result = Anagram.isAnagram("ABC", "bCA");
        assertTrue(result);
    }

    @Test
    public void falseAnagramTest() {
        final boolean result = Anagram.isAnagram("ABC", "BCB");
        assertFalse(result);
    }

    @Test
    public void falseWithNullAnagramTest() {
        final boolean result = Anagram.isAnagram("ABC", null);
        assertFalse(result);
    }
}
