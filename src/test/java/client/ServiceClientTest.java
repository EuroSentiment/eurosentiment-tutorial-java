package client;

import org.junit.Test;
import static org.junit.Assert.*;

public class ServiceClientTest {

    private String token = "78cab860-d17d-4afa-b1ac-d6ff84e942d7";

    private String serviceUrl = "http://217.26.90.243:8080/EuroSentimentServices/services/server/accessNoToken/sesld1129";

    @Test
    public void testServiceClient() {
        ServiceClient client = new ServiceClient(serviceUrl, token);
        NifInput input = new NifInput("{'input': 'El resultado debería ser castellano'}");
        NifOutput output = client.request(input);
        assertEquals(output.getJson().getJSONArray("entries").getJSONObject(0).get("dc:language"),
                     "Spanish");
    }
}
