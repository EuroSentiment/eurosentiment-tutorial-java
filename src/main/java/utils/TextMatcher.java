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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TextMatcher {

    @Autowired
    private TextNormalizer normalizer;

    public Map<String, Integer> matchesInText(String text, Collection<String> words) {
        Map<String, Integer> results = new HashMap<String, Integer>();
        String normalizedText = normalizer.normalizeText(text);
        List<String> tokenizedText = Arrays.asList(normalizedText.split("\\s+"));
        for(String word:words) {
            String normalizedWord = normalizer.normalizeText(word);
            int frequency = Collections.frequency(tokenizedText, normalizedWord);
            if(frequency > 0) {
                results.put(word, frequency);
            }
        }
        return results;
    }

}
