package org.rwtodd.agires;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AgiDictionary {
    private final HashMap<String,Integer> w2i;
    private final HashMap<Integer, ArrayList<String>> i2w;

    public AgiDictionary() {
        w2i = new HashMap<>();
        i2w = new HashMap<>();
    }

    public void addWord(final String word, int id) {
        w2i.put(word,id);
        i2w.compute(id, (k,v) -> {
            final var newV = ((v == null) ? new ArrayList<String>() : v);
            newV.add(word);
            return newV;
        });
    }

    public Iterable<String> idToWords(int id) {
        Iterable<String> ans = i2w.get(id);
        return (ans != null) ? ans : List.of();
    }

    public int wordToId(String word) {
        return w2i.getOrDefault(word, -1);
    }

    public Set<Integer> allIDs() {
        return i2w.keySet();
    }

    public Set<String> allWords() {
        return w2i.keySet();
    }
}
