package samples;

import java.text.MessageFormat;

public class SparqlQueryFactory {

    public static final String HOTEL_TOP_SENTIMENT = "PREFIX lemon: &lt;http://lemon-model.net/lemon#&gt;\n" +
            "PREFIX marl: &lt;http://purl.org/marl/ns/&gt;\n" +
            "SELECT ?entry count(?entry) as ?count WHERE {\n" +
            "    GRAPH &lt;http://www.eurosentiment.eu/dataset/hotel/%s/paradigma/lexicon&gt; {\n" +
            "        ?entry lemon:sense ?sense .\n" +
            "        ?sense marl:polarityValue ?polarityValue .\n" +
            "        ?sense lemon:reference ?reference .\n" +
            "    }\n" +
            "} GROUP BY ?entry ORDER BY DESC(?count) limit 10\n" ;

    public static final String ELECTRONICS_TOP_SENTIMENT = "PREFIX lemon: &lt;http://lemon-model.net/lemon#&gt;\n" +
            "PREFIX marl: &lt;http://purl.org/marl/ns/&gt;\n" +
            "SELECT ?entry count(?entry) as ?count WHERE {\n" +
            "    GRAPH &lt;http://www.eurosentiment.eu/dataset/electronics/%s/paradigma/lexicon&gt; {\n" +
            "        ?entry lemon:sense ?sense .\n" +
            "        ?sense marl:polarityValue ?polarityValue .\n" +
            "        ?sense lemon:reference ?reference .\n" +
            "    }\n" +
            "}GROUP BY ?entry ORDER BY DESC(?count) limit 10\n";

    public static final String ELECTRONICS_POSITIVE_ENTRIES = "PREFIX lemon: &lt;http://lemon-model.net/lemon#&gt;\n" +
            "PREFIX marl: &lt;http://purl.org/marl/ns/&gt;\n" +
            "SELECT ?entry WHERE {\n" +
            "    GRAPH &lt;http://www.eurosentiment.eu/dataset/electronics/%s/paradigma/lexicon&gt; {\n" +
            "        ?entry lemon:sense ?sense .\n" +
            "        ?sense marl:polarityValue ?polarityValue.\n" +
            "        ?sense marl:hasPolarity &lt;http://purl.org/marl/ns/positive&gt; .\n" +
            "        ?sense lemon:reference ?reference .\n" +
            "    }\n" +
            "} limit 10\n";

    public static final String HOTELS_POSITIVE_ENTRIES = "PREFIX lemon: &lt;http://lemon-model.net/lemon#&gt;\n" +
            "PREFIX marl: &lt;http://purl.org/marl/ns/&gt;\n" +
            "SELECT ?entry WHERE {\n" +
            "    GRAPH &lt;http://www.eurosentiment.eu/dataset/hotel/%s/paradigma/lexicon&gt; {\n" +
            "        ?entry lemon:sense ?sense .\n" +
            "        ?sense marl:polarityValue ?polarityValue.\n" +
            "        ?sense marl:hasPolarity &lt;http://purl.org/marl/ns/positive&gt; .\n" +
            "        ?sense lemon:reference ?reference .\n" +
            "    }\n" +
            "} limit 10\n";

    public static final String ELECTRONICS_NEGATIVE_ENTRIES = "PREFIX lemon: &lt;http://lemon-model.net/lemon#&gt;\n" +
            "PREFIX marl: &lt;http://purl.org/marl/ns/&gt;\n" +
            "SELECT ?entry WHERE {\n" +
            "    GRAPH &lt;http://www.eurosentiment.eu/dataset/electronics/%s/paradigma/lexicon&gt; {\n" +
            "        ?entry lemon:sense ?sense .\n" +
            "        ?sense marl:polarityValue ?polarityValue .\n" +
            "        ?sense marl:hasPolarity &lt;http://purl.org/marl/ns/negative&gt; .\n" +
            "        ?sense lemon:reference ?reference .\n" +
            "    }\n" +
            "}\n";

    public static final String HOTELS_NEGATIVE_ENTRIES = "PREFIX lemon: &lt;http://lemon-model.net/lemon#&gt;\n" +
            "PREFIX marl: &lt;http://purl.org/marl/ns/&gt;\n" +
            "SELECT ?entry WHERE {\n" +
            "    GRAPH &lt;http://www.eurosentiment.eu/dataset/hotel/%s/paradigma/lexicon&gt; {\n" +
            "        ?entry lemon:sense ?sense .\n" +
            "        ?sense marl:polarityValue ?polarityValue .\n" +
            "        ?sense marl:hasPolarity &lt;http://purl.org/marl/ns/negative&gt; .\n" +
            "        ?sense lemon:reference ?reference .\n" +
            "    }\n" +
            "}\n";

    public static final String ELECTRONICS_WORDS_POLARITIES = "PREFIX lemon: &lt;http://lemon-model.net/lemon#&gt;\n" +
            "PREFIX marl: &lt;http://purl.org/marl/ns/&gt;\n" +
            "SELECT DISTINCT ?wordWithSentiment ?polarityValue ?polarity ?sentimentEntry WHERE {\n" +
            "    GRAPH &lt;http://www.eurosentiment.eu/dataset/electronics/%s/paradigma/lexicon&gt; {\n" +
            "        ?sentimentEntry lemon:sense ?sense .\n" +
            "        ?sense marl:polarityValue ?polarityValue .\n" +
            "        ?sense marl:hasPolarity ?polarity .\n" +
            "        ?sense lemon:reference ?reference .\n" +
            "        ?sense lemon:context ?context .\n" +
            "        ?entryWithSentiment lemon:sense ?context .\n" +
            "        ?entryWithSentiment lemon:canonicalForm ?cf .\n" +
            "        ?cf lemon:writtenRep ?wordWithSentiment .\n" +
            "    }\n" +
            "}\n";

    public static final String HOTELS_WORDS_POLARITIES = "PREFIX lemon: &lt;http://lemon-model.net/lemon#&gt;\n" +
            "PREFIX marl: &lt;http://purl.org/marl/ns/&gt;\n" +
            "SELECT DISTINCT ?wordWithSentiment ?polarityValue ?polarity ?sentimentEntry WHERE {\n" +
            "    GRAPH &lt;http://www.eurosentiment.eu/dataset/hotel/%s/paradigma/lexicon&gt; {\n" +
            "        ?sentimentEntry lemon:sense ?sense .\n" +
            "        ?sense marl:polarityValue ?polarityValue .\n" +
            "        ?sense marl:hasPolarity ?polarity .\n" +
            "        ?sense lemon:reference ?reference .\n" +
            "        ?sense lemon:context ?context .\n" +
            "        ?entryWithSentiment lemon:sense ?context .\n" +
            "        ?entryWithSentiment lemon:canonicalForm ?cf .\n" +
            "        ?cf lemon:writtenRep ?wordWithSentiment .\n" +
            "    }\n" +
            "}\n";

    public static String getQuery(String query, String language) {
        return String.format(query, language);
    }

}