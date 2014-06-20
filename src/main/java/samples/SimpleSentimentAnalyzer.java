package samples;

import org.json.JSONObject;

public class SimpleSentimentAnalyzer {

    public JSONObject getSentiment(String text) {
        return new JSONObject("{'sentiment': 7.3}");
    }

}
