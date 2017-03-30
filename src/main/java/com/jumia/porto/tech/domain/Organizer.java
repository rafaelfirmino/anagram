package com.jumia.porto.tech.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static java.util.Arrays.stream;

/**
 * @author rgfirmino
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 3/29/17 7:55 PM
 */
public final class Organizer {

    private Organizer() {
    }

    public static List<String> sortListAndRemoveDuplicates(final String[] entries) {
        final Set<String> entriesSet = new TreeSet<>();
        stream(entries)
                .forEach(e -> entriesSet.add(e));
        return Arrays.asList(entriesSet.toArray(new String[entries.length]));
    }

    public static String sortAscWords(final String word) {
        final char[] array = word.toUpperCase().toCharArray();
        Arrays.sort(array);
        return String.valueOf(array);
    }
}
