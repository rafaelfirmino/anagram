package com.jumia.porto.tech.service;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author rgfirmino
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 3/29/17 8:21 PM
 */
public class AnagramServiceTest {

    public static final String ALPHA_DIGITS = "[^a-zA-Z]+";
    private AnagramService anagramService;

    @Before
    public void setUp() {
        anagramService = new AnagramService();
    }

    @Test
    public void processEntriesToAnagramTest() {
        final Map<String, List<String>> result = anagramService
                .processEntries("anagram", "gramana", "fakefake", "akeakeff",
                        "noanagram", "iiiiiiiiiibbbbbbbbbb", "bibibibibibibibibibi",
                        "efakefak", "kefakefa", "akefakef");

        assertNotNull(result);

        final Iterator<String> iterator = result.keySet().iterator();

        final String akeakeff = iterator.next();
        assertEquals("akeakeff", akeakeff);
        assertEquals("[akefakef, efakefak, fakefake, kefakefa]", String.valueOf(result.get(akeakeff)));

        final String anagram = iterator.next();
        assertEquals("anagram", anagram);
        assertEquals("[gramana]", String.valueOf(result.get(anagram)));

        final String bibibibibibibibibibi = iterator.next();
        assertEquals("bibibibibibibibibibi", bibibibibibibibibibi);
        assertEquals("[iiiiiiiiiibbbbbbbbbb]", String.valueOf(result.get(bibibibibibibibibibi)));

        final String noanagram = iterator.next();
        assertEquals("noanagram", noanagram);
        assertEquals("[]", String.valueOf(result.get(noanagram)));
    }

    @Test
    public void processEntriesToAnagramWithRandomDataTest() {

        final String a = "0_" + UUID.randomUUID().toString().replaceAll(ALPHA_DIGITS, "");
        final String b = "1_" + UUID.randomUUID().toString().replaceAll(ALPHA_DIGITS, "");
        final String c = "2_" + UUID.randomUUID().toString().replaceAll(ALPHA_DIGITS, "");

        final Map<String, List<String>> result = anagramService
                .processEntries(a, b, c, shuffle(a), shuffle(a), shuffle(b), shuffle(c), shuffle(a), "nodata", "no");

        assertNotNull(result);

        final Iterator<String> iterator = result.keySet().iterator();

        assertEquals(3, result.get(iterator.next()).size());
        assertEquals(1, result.get(iterator.next()).size());
        assertEquals(1, result.get(iterator.next()).size());
        assertEquals(0, result.get(iterator.next()).size());
        assertEquals(0, result.get(iterator.next()).size());
    }

    private String shuffle(final String input) {
        final List<Character> characters = new ArrayList<>();
        for (final char c : input.toCharArray()) {
            characters.add(c);
        }
        final StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0) {
            final int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }
}
