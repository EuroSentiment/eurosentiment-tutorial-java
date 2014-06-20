package client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ServiceClient {

    private String serviceUrl;
    private String token;
    private WebResource resource;

    public ServiceClient(String serviceUrl, String token) {
        this.serviceUrl = serviceUrl;
        this.token = token;
        Client client = Client.create();
        this.resource = client.resource(serviceUrl);
    }

    public NifOutput request(NifInput input) {
        WebResource.Builder builder = this.resource.header("x-eurosentiment-token", this.token)
                                                   .header("content-type", "application/json");
        ClientResponse response = builder.post(ClientResponse.class, input.asJson());
        String result = response.getEntity(String.class);
        return new NifOutput(result);
    }

}
