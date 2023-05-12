package tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.Tasks;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;

public class Task3 {
    private static String url = "https://jsonplaceholder.typicode.com";
    private static HttpClient client = HttpClient.newHttpClient();
    public static Collection<Tasks>  getNotFinishedTasks(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/users/" + userId + "/todos?completed=false"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        Type tasks = new TypeToken<Collection<Tasks>>(){}.getType();
        return new Gson().fromJson(response.body(), tasks);
    }
}
