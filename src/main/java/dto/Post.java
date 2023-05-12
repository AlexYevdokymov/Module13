package dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;

    public int getId() {
        return this.id;
    }
    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);
        return json;
    }
}
