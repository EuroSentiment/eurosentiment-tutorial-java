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

import client.NifOutput;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SimpleSentimentAnalyzer {

    private static final Logger log = Logger.getLogger(SimpleSentimentAnalyzer.class);

    @Autowired
    private PositiveWordsMatcher positiveWordsMatcher;

    @Autowired
    private NegativeWordsMatcher negativeWordsMatcher;

    public NifOutput getSentiment(String text) {
        log.info("Retrieving negative matches...");
        Map<String, Integer> negativeWords = negativeWordsMatcher.getNegativeWords(text);
        log.info("Retrieving positive matches...");
        Map<String, Integer> positiveWords = positiveWordsMatcher.getPositiveWords(text);
        double sentiment = calculateSentiment(positiveWords, negativeWords);
        return new NifOutput("{\"@context\": \"http://eurosentiment.eu/contexts/basecontext.jsonld\"," +
                             "\"@type\":\"marl:SentimentAnalysis\"," +
                             "\"marl:polarityValue\":" + sentiment +"}");

    }

    private double calculateSentiment(Map<String, Integer> positiveWords, Map<String, Integer> negativeWords) {
        int positiveCount = sumMapValues(positiveWords);
        log.info("Number of positive words:" + positiveCount);
        int negativeCount = sumMapValues(negativeWords);
        log.info("Number of negative words:" + negativeCount);
        if ((positiveCount + negativeCount) > 0) {
            return ((double)(positiveCount-negativeCount))/(positiveCount + negativeCount);
        }
        return 0.0d;
    }

    private int sumMapValues(Map<String, Integer> words) {
        int result = 0;
        for(Integer count:words.values()) {
            result += count;
        }
        return result;
    }

}
