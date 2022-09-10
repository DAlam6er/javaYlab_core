package com.ylab.hometasks.task03;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

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

    public static boolean fuzzySearch(String occurrence, String original)
    {
        if (occurrence == null || original == null) return false;

        String regex = occurrence.codePoints()
            .flatMap(s ->
                (String.format(".*%s.*", new String(Character.toChars(s))))
                    .codePoints())
            .collect(StringBuilder::new,
                StringBuilder::appendCodePoint,
                StringBuilder::append)
            .toString();
        return Pattern.matches(regex, original);
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
