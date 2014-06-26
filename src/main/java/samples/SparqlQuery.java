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

import java.util.HashMap;
import java.util.Map;

public class SparqlQuery {

    public class Language {

        public static final String ENGLISH = "en";

        public static final String SPANISH = "es";

    }

    public class Domain {

        public static final String ELECTRONICS = "electronics";

        public static final String HOTEL = "hotel";
    }

    private String language;

    private String domain;

    private String prefix;

    private String query;

    private Map<String, Map<String, String>> prefixesDictionary;

    public SparqlQuery(String query) {
        this.language = "";
        this.domain = "";
        this.query = query;
        this.prefixesDictionary = new HashMap<String, Map<String, String>>();
        Map<String, String> prefixByDomainES = new HashMap<String, String>();
        Map<String, String> prefixByDomainEN = new HashMap<String, String>();
        prefixByDomainEN.put(Domain.ELECTRONICS, "gabvul/2");
        prefixByDomainES.put(Domain.ELECTRONICS, "gabvul/3");
        prefixByDomainEN.put(Domain.HOTEL, "gabvul/8");
        prefixByDomainES.put(Domain.HOTEL, "gabvul/9");
        prefixesDictionary.put(Language.ENGLISH, prefixByDomainEN);
        prefixesDictionary.put(Language.SPANISH, prefixByDomainES);
    }

    public SparqlQuery(String prefix, String query) {
        this.language = "";
        this.domain = "";
        this.query = "";
        this.prefix = prefix;
    }

    public String getGraphUri(String language, String domain) {
        this.language = language;
        this.domain = domain;
        return this.getGraphUri();
    }

    public String getPrefix() {
        if (!this.isValid()) {
            throw new IllegalStateException("SparQL query must have not empty domain or language");
        }
        if(prefix != null) {
            return prefix;
        }
        return prefixesDictionary.get(this.language).get(this.domain);
    }

    public String getSuffix() {
        return "lexicon";
    }

    public String getGraphUri() {
        if (!this.isValid()) {
            throw new IllegalStateException("SparQL query must have not empty domain or language");
        }
        return new StringBuilder().append("http://www.eurosentiment.eu/dataset/").append(domain).
                append("/").append(language).append("/").append(getPrefix()).append("/").
                append(getSuffix()).toString();
    }

    public String getSparql(String language, String domain) {
        return String.format(this.query, this.getGraphUri(language, domain));
    }

    private boolean isValid() {
        return !this.language.isEmpty() && !this.domain.isEmpty();
    }


}
