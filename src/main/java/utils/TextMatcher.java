package utils;

import java.util.*;

public class TextMatcher {

    public static Map<String, Integer> matchesInText(String text, List<String> words) {
        Map<String, Integer> results = new HashMap<String, Integer>();
        List<String> tokenizedText = Arrays.asList(text.split("\\s+"));
        for(String word:words) {
            String normalizedWord = TextNormalizer.normalizeText(word);
            int frequency = Collections.frequency(tokenizedText, normalizedWord);
            if(frequency > 0) {
                results.put(word, frequency);
            }
        }
        return results;
    }

}
