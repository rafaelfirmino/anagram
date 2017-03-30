package com.jumia.porto.tech.boot;

import com.jumia.porto.tech.domain.FileProcessor;
import com.jumia.porto.tech.service.AnagramService;

import java.util.List;
import java.util.Map;

/**
 * @author rgfirmino
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 3/29/17 10:34 PM
 */
public class StartApplication {

    public static void main(String[] args) {

        final AnagramService anagramService = new AnagramService();

        final String[] entries = FileProcessor.buildEntries("src/main/resources/input.txt");
        final Map<String, List<String>> result = anagramService.processEntries(entries);
        FileProcessor.processResult(result, "src/main/resources/output.txt");
        logResult(result);
    }

    private static void logResult(final Map<String, List<String>> result) {
        System.out.println(result);
    }
}
