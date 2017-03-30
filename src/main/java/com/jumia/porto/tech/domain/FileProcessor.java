package com.jumia.porto.tech.domain;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author rgfirmino
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 3/29/17 10:37 PM
 */
public final class FileProcessor {

    public static String[] buildEntries(final String inputPath) {
        final List<String> entries = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(inputPath))) {

            stream.forEach(l -> entries.add(l));
        } catch (final IOException e) {
            throw new IllegalArgumentException("Input not exist.", e);
        }

        return entries.toArray(new String[entries.size()]);
    }

    public static void processResult(final Map<String, List<String>> result, final String outputPath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputPath))) {
            writer.write(result.toString());
        } catch (final IOException e) {
            throw new IllegalArgumentException("Output not exist.", e);
        }
    }
}
