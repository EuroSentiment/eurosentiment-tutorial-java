package samples;

import client.NifInput;
import client.NifOutput;
import client.ResourceClient;
import client.ServiceClient;
import org.json.JSONObject;

public class NegativeWordsMatcher {

    private ServiceClient languageDetector;

    public NegativeWordsMatcher() {
        this.languageDetector = new ServiceClient("url", "token");
    }

    public JSONObject getNegativeWords(String text) {
        NifOutput languageResult = this.languageDetector.request(new NifInput("{'input':" + text + "}"));
        String language = languageResult.getJson().getJSONArray("entries").getJSONObject(0).getString("dc:language");
        ResourceClient resourceClient = getResourceClient(language);
        NifOutput wordsResults = resourceClient.request(
                new NifInput("{'query':" + SparqlQueryFactory.getQuery(SparqlQueryFactory.ELECTRONICS_NEGATIVE_ENTRIES, language) + ", " +
                        "'format':'application/sparql-results+json'}"));
        return wordsResults.getJson();
    }

    public ResourceClient getResourceClient(String language) {
        ResourceClient resourceClient = new ResourceClient("", "");
        return resourceClient;
    }


}
