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

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SimpleSentimentAnalyzer {

    @Autowired
    private PositiveWordsMatcher positiveWordsMatcher;

    @Autowired
    private NegativeWordsMatcher negativeWordsMatcher;

    public JSONObject getSentiment(String text) {
        System.out.println(negativeWordsMatcher);
        positiveWordsMatcher.getPositiveWords("Esto es un texto en castellano y blablablabla");
        return new JSONObject("{'sentiment': 7.3}");
    }

}
