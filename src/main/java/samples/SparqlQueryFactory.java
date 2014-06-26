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

public class SparqlQueryFactory {



    public static final SparqlQuery POSITIVE_ENTRIES = new SparqlQuery("PREFIX lemon: <http://lemon-model.net/lemon#> PREFIX marl: <http://purl.org/marl/ns/> SELECT DISTINCT ?wordWithSentiment ?polarityValue ?sentimentEntry from <%s> where { ?sentimentEntry lemon:sense ?sense . ?sense marl:polarityValue ?polarityValue . ?sense marl:hasPolarity <http://purl.org/marl/ns/positive> . ?sense lemon:reference ?reference . ?sense lemon:context ?context . ?entryWithSentiment lemon:sense ?context . ?entryWithSentiment lemon:canonicalForm ?cf . ?cf lemon:writtenRep ?wordWithSentiment . }");

    public static final SparqlQuery NEGATIVE_ENTRIES = new SparqlQuery("PREFIX lemon: <http://lemon-model.net/lemon#> PREFIX marl: <http://purl.org/marl/ns/> SELECT DISTINCT ?wordWithSentiment ?polarityValue ?sentimentEntry from <%s> where { ?sentimentEntry lemon:sense ?sense . ?sense marl:polarityValue ?polarityValue . ?sense marl:hasPolarity <http://purl.org/marl/ns/negative> . ?sense lemon:reference ?reference . ?sense lemon:context ?context . ?entryWithSentiment lemon:sense ?context . ?entryWithSentiment lemon:canonicalForm ?cf . ?cf lemon:writtenRep ?wordWithSentiment . }");

    public static String getSparql(SparqlQuery query, String language, String domain) {
        return query.getSparql(language, domain);
    }

}