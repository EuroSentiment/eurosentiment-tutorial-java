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

import java.util.concurrent.atomic.AtomicLong;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.WebApplicationException;

@RestController
public class Server {

    @Autowired
    SimpleSentimentAnalyzer analyzer;

    @RequestMapping(value = "/sentiment",
                    method = RequestMethod.POST,
                    produces = {"application/json"})
    public String getSentiment(@RequestBody String input) {
        try {
            JSONObject jsonInput = new JSONObject(input);
            return analyzer.getSentiment(jsonInput.getString("input")).toString();
        } catch(Exception e) {
            throw new WebApplicationException(500);
        }
    }

}
