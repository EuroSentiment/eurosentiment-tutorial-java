package client;

import org.json.JSONObject;

public class NifInput {

    protected JSONObject json = new JSONObject();

    public NifInput(String jsonString) {
        this.json = new JSONObject(jsonString);
    }

    public String asJson() {
        return json.toString();
    }
}
