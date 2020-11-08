package org.rwtodd.agi.extractor;

import java.util.List;
import java.util.stream.Stream;
import java.util.Map.Entry;

/**
 * An interface to lookup AGI words.
 *
 * @author rwtodd
 */
public interface WordDictionary {

    public int lookupWord(String word);

    public List<String> lookupGroup(int group);

    public Stream<Entry<String, Integer>> wordStream();

    public Stream<Entry<Integer, List<String>>> groupStream();
}
