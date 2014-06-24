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

import client.NifInput;
import client.NifOutput;
import client.ResourceClient;
import client.ServiceClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PositiveWordsMatcher {

    @Value("${eurosentiment.language.detection.url}")
    private String languageDetectionServiceUrl;

    @Value("${eurosentiment.token}")
    private String token;

    private ServiceClient languageDetector;

    public PositiveWordsMatcher() {
        this.languageDetector = new ServiceClient(this.languageDetectionServiceUrl, this.token);
    }

    public JSONObject getPositiveWords(String text) {
        NifOutput languageResult = this.languageDetector.request(new NifInput("{'input':" + text + "}"));
        String language = languageResult.getJson().getJSONArray("entries").getJSONObject(0).getString("dc:language");
        ResourceClient resourceClient = getResourceClient(language);
        NifOutput wordsResults = resourceClient.request(
                new NifInput("{'query':" + SparqlQueryFactory.getQuery(SparqlQueryFactory.ELECTRONICS_POSITIVE_ENTRIES, language) + ", " +
                             "'format':'application/sparql-results+json'}"));
        return wordsResults.getJson();
    }

    public ResourceClient getResourceClient(String language) {
        ResourceClient resourceClient = new ResourceClient("", "");
        return resourceClient;
    }

}
