package utils;

import java.text.Normalizer;

public class TextNormalizer {

    public static String normalizeText(String text) {
        return text == null ? null
                : Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
    }

}
