package client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ResourceClient {

    private String resourceUrl;
    private String token;
    private WebResource resource;

    public ResourceClient(String resourceUrl, String token) {
        this.resourceUrl = resourceUrl;
        this.token = token;
        Client client = Client.create();
        this.resource = client.resource(resourceUrl);
    }

    public NifOutput request(NifInput input) {
        WebResource.Builder builder = this.resource.header("x-eurosentiment-token", this.token)
                                                   .header("content-type", "application/json");
        ClientResponse response = builder.post(ClientResponse.class, input.asJson());
        String result = response.getEntity(String.class);
        return new NifOutput(result);
    }
}
