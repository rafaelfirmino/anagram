package com.jumia.porto.tech.service;

import com.jumia.porto.tech.domain.Anagram;
import com.jumia.porto.tech.domain.Organizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author rgfirmino
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 3/29/17 7:45 PM
 */
public final class AnagramService {

    public Map<String, List<String>> processEntries(final String... entries) {
        if (entries == null || entries.length <= 0) {
            throw new IllegalArgumentException("Entries cannot be null or empty.");
        }

        final List<String> cleanAndSortedEntries = Organizer.sortListAndRemoveDuplicates(entries);
        final Set<String> sortedKeys = sortedKeys(cleanAndSortedEntries);
        final SortedMap<String, List<String>> resultSortedMap = new TreeMap<>();
        final SortedMap<String, List<String>> sortedMap = new TreeMap<>();

        populateSortedMap(cleanAndSortedEntries, sortedKeys, sortedMap);
        populateResultMap(resultSortedMap, sortedMap);

        return resultSortedMap;
    }

    private void populateSortedMap(final List<String> cleanAndSortedEntries, final Set<String> sortedKeys,
            final SortedMap<String, List<String>> sortedMap) {
        for (final String sortedKeyIndex : sortedKeys) {
            final List<String> list = new ArrayList<>();
            //High complexity; Two fors inside; To review complexity
            for (final String cleanEntry : cleanAndSortedEntries) {
                if (sortedKeyIndex.equals(cleanEntry)) {
                    continue;
                }

                if (Anagram.isAnagram(sortedKeyIndex, cleanEntry)) {
                    list.add(cleanEntry);
                }
            }
            sortedMap.put(sortedKeyIndex, list);
        }
    }

    private void populateResultMap(final SortedMap<String, List<String>> resultSortedMap,
            final SortedMap<String, List<String>> sortedMap) {
        sortedMap.forEach((key, value) -> {
            if (value.isEmpty()) {
                return;
            }

            final String keyResultMap = value.iterator().next();
            value.remove(keyResultMap);

            resultSortedMap.put(keyResultMap, value);
        });
    }

    private Set<String> sortedKeys(final List<String> cleanEntries) {
        final Set<String> sortedKeys = new TreeSet<>();
        for (final String cleanEntry : cleanEntries) {
            sortedKeys.add(Organizer.sortAscWords(cleanEntry));
        }
        return sortedKeys;
    }
}
