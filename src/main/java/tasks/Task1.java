package tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;

public class Task1 {
    private static String url = "https://jsonplaceholder.typicode.com";
    private static HttpClient client = HttpClient.newHttpClient();
    public static String postUser(User user) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/users"))
                .header("Content","UserJson")
                .POST(HttpRequest.BodyPublishers.ofString(user.toString()))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static String putUser(User user, int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/users/" + userId))
                .header("Content","UserJson")
                .PUT(HttpRequest.BodyPublishers.ofString(user.toString()))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    public static int deleteUser(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/users/" + userId))
                .header("Content", "UserJson")
                .DELETE()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }
    public static Collection<User> getAllUsers() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/users"))
                .GET()
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        Type users = new TypeToken<Collection<User>>(){}.getType();
        return new Gson().fromJson(response.body(), users);
    }

    public static User getUserById(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/users/" + userId))
                .GET()
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        return new Gson().fromJson(response.body(), User.class);
    }

    public static Collection<User> getUsersByUsername(String userName) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/users?username=" + userName))
                .GET()
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        Type users = new TypeToken<Collection<User>>(){}.getType();
        return new Gson().fromJson(response.body(), users);
    }
}
