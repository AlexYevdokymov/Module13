package tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dto.Comment;
import dto.Post;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.List;

public class Task2 {
    private static String url = "https://jsonplaceholder.typicode.com";
    private static HttpClient client = HttpClient.newHttpClient();

    private static int getMaxId(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/users/" + userId + "/posts"))
                .GET()
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        Type posts = new TypeToken<Collection<Post>>(){}.getType();
        List<Post> postList = new Gson().fromJson(response.body(), posts);
        return postList.stream().map(Post::getId).mapToInt(Integer::intValue).max().getAsInt();
    }

    public static Collection<Comment> getUsersLastPostComments(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/posts/" + getMaxId(userId) + "/comments"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        Type comments = new TypeToken<Collection<Comment>>(){}.getType();
        return new Gson().fromJson(response.body(), comments);
    }

    public static void writeComments(int userId) throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(getUsersLastPostComments(userId));
        File userFile = new File("user-" + userId + "-post-"+ getMaxId(userId) +"-comments.json");
        try (FileWriter writer = new FileWriter(userFile))
        {
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
