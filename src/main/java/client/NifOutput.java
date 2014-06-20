package client;

import org.json.JSONObject;

public class NifOutput {

    JSONObject json;

    public NifOutput(String jsonString) {
        json = new JSONObject(jsonString);
    }

    public JSONObject getJson() {
        return json;
    }

}
