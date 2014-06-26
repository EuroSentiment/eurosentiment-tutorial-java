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
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class NegativeWordsMatcher extends WordsMatcher {

    private static final Logger log = Logger.getLogger(NegativeWordsMatcher.class);

    public Map<String, Integer> getNegativeWords(String text) {
        NifOutput languageResult = this.languageDetector.request(new NifInput("{'text':'" + text + "'}"));
        String language = languageResult.getJson().getString("dc:language");
        log.info("The detected language is [" + language + "]");
        NifOutput domainResult = this.domainDetector.request(new NifInput("{'text':'" + text + "'}"));
        String domain = domainResult.getJson().getString("domain");
        String domainName = domain.split(":")[1];
        log.info("The detected domain is [" + domainName + "]");
        String query = SparqlQueryFactory.getSparql(SparqlQueryFactory.NEGATIVE_ENTRIES, language, domainName);
        NifInput input = new NifInput("{'query':'" + query + "', " +
                "'format':'application/json'}");
        NifOutput wordsResults = this.resourceClient.request(input);
        Set<String> sentimentWords = extractWordsListFromResponse(wordsResults.getJson());
        log.info(sentimentWords.size() + " positive words retrieved.");
        return matchWords(text, sentimentWords);
    }

    @PostConstruct
    public void initialized() throws Exception {
        this.languageDetector = new ServiceClient(this.languageDetectionServiceUrl, this.token);
        this.domainDetector = new ServiceClient(this.domainDetectionServiceUrl, this.token);
        this.resourceClient =  new ResourceClient(this.resourcesUrl, this.token);
    }

}
