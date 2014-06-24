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
package samples;

import client.ResourceClient;
import client.ServiceClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import utils.TextMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public abstract class WordsMatcher {

    @Value("${eurosentiment.language.detection.url}")
    protected String languageDetectionServiceUrl;

    @Value("${eurosentiment.resources.url}")
    protected String resourcesUrl;

    @Value("${eurosentiment.token}")
    protected String token;

    protected ServiceClient languageDetector;

    protected ResourceClient resourceClient;

    @Autowired
    private TextMatcher textMatcher;

    protected List<String> extractWordsListFromResponse(JSONObject wordsResults) {
        List<String> result = new ArrayList<String>();
        JSONArray bindings = wordsResults.getJSONObject("results").getJSONArray("bindings");
        for(int i=0; i<bindings.length(); i++) {
            JSONObject word = bindings.getJSONObject(i);
            result.add(word.getJSONObject("wordWithSentiment").getString("value"));
        }
        return result;
    }

    protected Map<String, Integer> matchWords(String text, List<String> words) {
        return textMatcher.matchesInText(text, words);
    }


}
