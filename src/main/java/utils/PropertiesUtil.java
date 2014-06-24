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
package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.TreeSet;

public class PropertiesUtil {

    private Properties properties;

    public PropertiesUtil(String propertiesFile) throws FileNotFoundException {
        try {
            properties = new Properties();
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream(propertiesFile));
        } catch (IOException e) {
            throw new FileNotFoundException("Properties file [" + propertiesFile + "] could not be found");
        }
    }

    public String getProperty(String property) {
        return properties.getProperty(property);
    }


    public int getIntProperty(String property) throws NumberFormatException {
        String stringValue = properties.getProperty(property);
        int intValue = 0;
        try {
            intValue = Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid int value [" + stringValue + "] for property [" + property + "]");
        }
        return intValue;
    }

   public TreeSet<String> listProperties() {
        return new TreeSet(properties.stringPropertyNames());
    }
}
