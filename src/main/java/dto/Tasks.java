package dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Tasks {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);
        return json;
    }
}
