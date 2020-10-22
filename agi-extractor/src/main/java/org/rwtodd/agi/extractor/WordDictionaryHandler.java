package org.rwtodd.agi.extractor;

import org.rwtodd.agi.resources.WordsResource;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Stream;

/**
 *
 * @author rwtodd
 */
public class WordDictionaryHandler implements WordsResource.Handler {
    private final Map<String,Integer> wordMap;
    private final Map<Integer,List<String>> groupMap;
    
    public WordDictionaryHandler() {
        wordMap = new HashMap<>();
        groupMap = new HashMap<>();
    }
    
    public int lookupWord(String word) {
        return wordMap.getOrDefault(word, -1);
    }
    
    public List<String> lookupGroup(int group) {
        final var list = groupMap.get(group);
        return (list==null)?List.of():list;
    }

    public Stream<Entry<String,Integer>> wordStream() {
        return wordMap.entrySet().stream();
    }
    
    public Stream<Entry<Integer,List<String>>> groupStream() {
        return groupMap.entrySet().stream();
    }
    
    @Override
    public void wordsStart() {
        /* do nothing */
    }

    @Override
    public void word(String word, int group) {
        wordMap.put(word, group);
        groupMap.computeIfAbsent(group, k -> new ArrayList<>()).add(word);
    }

    @Override
    public void wordsEnd() {
        // convert all lists to unmodifiable lists, for safety.
        groupMap.replaceAll((group,list) -> java.util.Collections.unmodifiableList(list));
    }
}
