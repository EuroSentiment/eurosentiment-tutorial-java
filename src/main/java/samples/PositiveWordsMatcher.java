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
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import utils.PropertiesUtil;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.Arrays;

@Component
public class PositiveWordsMatcher {

    @Value("${eurosentiment.language.detection.url}")
    private String languageDetectionServiceUrl;

    @Value("${eurosentiment.resources.url}")
    private String resourcesUrl;

    @Value("${eurosentiment.token}")
    private String token;

    private ServiceClient languageDetector;

    private ResourceClient resourceClient;

    public JSONObject getPositiveWords(String text) {
        NifOutput languageResult = this.languageDetector.request(new NifInput("{'text':" + text + "}"));
        String language = languageResult.getJson().getString("dc:language");
        String query = SparqlQueryFactory.getQuery(SparqlQueryFactory.ELECTRONICS_POSITIVE_ENTRIES, language);
        NifInput input = new NifInput("{'query':'" + query + "', " +
                                       "'format':'application/json'}");
        NifOutput wordsResults = this.resourceClient.request(input);
        return wordsResults.getJson();
    }

    @PostConstruct
    public void initClients() {
        this.languageDetector = new ServiceClient(this.languageDetectionServiceUrl, this.token);
        this.resourceClient =  new ResourceClient(this.resourcesUrl, this.token);
    }

}
