package samples;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Path("/eurosentiment")
public class Application {

    SimpleSentimentAnalyzer analyzer = new SimpleSentimentAnalyzer();


    @Path("sentiment")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String getSentiment(@DefaultValue("") @QueryParam("input") String text) {
        return analyzer.getSentiment(text).toString();
    }

    public static void main(String[] args) throws IOException {
        final String baseUri = "http://0.0.0.0:9998/";

        final Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("com.sun.jersey.config.property.packages", "samples");
        SelectorThread threadSelector = GrizzlyWebContainerFactory.create(baseUri, initParams);
        System.out.println(String.format("EUROSENTIMENT services running in %s", baseUri));
    }

}
