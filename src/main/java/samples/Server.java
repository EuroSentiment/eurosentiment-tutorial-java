package samples;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Path("/eurosentiment")
public class Server {

    SimpleSentimentAnalyzer analyzer = new SimpleSentimentAnalyzer();


    @Path("sentiment")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getSentiment(String input) {
        try {
            JSONObject jsonInput = new JSONObject(input);
            return analyzer.getSentiment(jsonInput.getString("input")).toString();
        } catch(Exception e) {
            throw new WebApplicationException(500);
        }
    }

    public static void main(String[] args) throws IOException {
        final String baseUri = "http://0.0.0.0:9998/";

        final Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("com.sun.jersey.config.property.packages", "samples");
        SelectorThread threadSelector = GrizzlyWebContainerFactory.create(baseUri, initParams);
        System.out.println(String.format("EUROSENTIMENT services running in %s", baseUri));
    }

}
