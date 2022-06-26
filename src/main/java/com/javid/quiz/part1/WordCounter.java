package com.javid.quiz.part1;

import java.util.LinkedHashMap;
import java.util.Map;

public class WordCounter {

    public Map<String, Integer> getWordMap(String sentence) {
        var map = new LinkedHashMap<String, Integer>();

        for (var w : fetchWords(sentence)) {
            addWordToMap(map, w);
        }

        return map;
    }

    private String[] fetchWords(String sentence) {
        if (sentence == null) {
            return new String[]{};
        }

        return sentence
                .replaceAll("[^a-zA-Z0-9]", " ")
                .split("\\s+");
    }

    private void addWordToMap(Map<String, Integer> map, String w) {
        if (map.containsKey(w)) {
            var newValue = map.get(w) + 1;
            map.replace(w, newValue);
        } else {
            map.put(w, 1);
        }
    }

}
