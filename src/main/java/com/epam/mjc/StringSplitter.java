package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<>();
        int beginIndex = 0;
        for (int i = 0; i < source.length(); i++) {
            for (Iterator<String> it = delimiters.iterator(); it.hasNext(); ) {
                String delimiter = it.next();
                if (source.regionMatches(i, delimiter, 0, delimiter.length())) {
                    if (beginIndex != i) {
                        result.add(source.substring(beginIndex, i + delimiter.length() - 1));
                    }
                    beginIndex = i + delimiter.length();
                    break;
                }
            }
        }
        if (beginIndex < source.length()) {
            result.add(source.substring(beginIndex));
        }
        return result;
    }
}