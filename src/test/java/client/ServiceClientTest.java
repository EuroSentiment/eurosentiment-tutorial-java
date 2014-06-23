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
