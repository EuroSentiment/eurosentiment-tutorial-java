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
package client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;

public class ServiceClient {

    private static final Logger log = Logger.getLogger(ServiceClient.class);

    private String token;

    private WebResource resource;

    private String serviceUrl;

    public ServiceClient(String serviceUrl, String token) {
        this.token = token;
        this.serviceUrl = serviceUrl;
        Client client = Client.create();
        if(serviceUrl != null) {
            this.resource = client.resource(serviceUrl);
        }
    }

    public NifOutput request(NifInput input) {
        WebResource.Builder builder = this.resource.header("x-eurosentiment-token", this.token)
                                                   .header("content-type", "application/json");
        log.info("Request to Services Server at [" + serviceUrl + "] with body: " + input);
        ClientResponse response = builder.post(ClientResponse.class, input.asJson());
        String result = response.getEntity(String.class);
        log.info("Response:" + result);
        return new NifOutput(result);
    }

}
