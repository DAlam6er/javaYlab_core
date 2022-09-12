package com.ylab.hometasks.task03;

import java.util.Map;
import java.util.Objects;

/*
Task3
    Реализовать функцию нечеткого поиска

            fuzzySearch("car", "ca6$$#_rtwheel"); // true
            fuzzySearch("cwhl", "cartwheel"); // true
            fuzzySearch("cwhee", "cartwheel"); // true
            fuzzySearch("cartwheel", "cartwheel"); // true
            fuzzySearch("cwheeel", "cartwheel"); // false
            fuzzySearch("lw", "cartwheel"); // false
 */
public class FuzzySearch
{
    public static void main(String[] args)
    {
        Map<String, String> map = Map.of(
            "car", "ca6$$#_rtwheel",
            "cwhl", "cartwheel",
            "cwhee", "cartwheel",
            "cartwheel", "cartwheel",
            "cwheeel", "cartwheel",
            "lw", "cartwheel"
        );
        formattedTestPrint(map);
    }

    // ВАРИАНТ 1
    /*
    public static boolean fuzzySearch(String occurrence, String original)
    {
        if (occurrence == null || original == null) return false;
        if ((occurrence.length() == 0) ^ (original.length() == 0)) {
            return false;
        }

        int j;
        int k = -1;
        boolean occurFound;
        for (int i = 0; i < occurrence.length(); i++) {
            occurFound = false;
            for (j = k + 1; j < original.length(); j++) {
                if (occurrence.codePointAt(i) == original.codePointAt(j)) {
                    k = j;
                    occurFound = true;
                    break;
                }
            }
            if (!occurFound) return false;
        }
        return true;
    }
     */

    // ВАРИАНТ 2
    public static boolean fuzzySearch(String occurrence, String original)
    {
        if (occurrence == null || original == null) return false;
        if ((occurrence.length() == 0) ^ (original.length() == 0)) {
            return false;
        }

        int i = 0;
        int j = 0;
        while ((i < occurrence.length()) && (j < original.length())) {
            if (occurrence.codePointAt(i) == original.codePointAt(j)) {
                i++;
            }
            j++;
        }

        return (i == occurrence.length());
    }

    public static void formattedTestPrint(Map<String, String> map)
    {
        if (map == null) return;

        int maxLenKey = map.keySet().stream()
            .filter(Objects::nonNull).map(String::length)
            .max(Integer::compare).orElse(0);
        int maxLenValue = map.values().stream()
            .filter(Objects::nonNull).map(String::length)
            .max(Integer::compare).orElse(0);

        map.forEach(
            (k, v) ->
                System.out.printf(
                    "%" + maxLenKey + "s in %" + maxLenValue + "s: %b\n",
                    k, v, fuzzySearch(k, v))
        );
    }
}
