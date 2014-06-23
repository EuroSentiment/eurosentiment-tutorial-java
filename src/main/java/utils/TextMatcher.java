/**
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 *
 *  The original code are licensed under the GNU Lesser General Public License.
 */
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
