package de.adminelse.antibot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Utils {

    public static JsonObject getJsonResponse(String ip) {
        // Create an HTTP client
        HttpClient httpClient = HttpClient.newHttpClient();

        // Create an HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://ipwhois.app/widget.php?ip="+ip+"&lang=en"))
                .GET()
                .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36")
                .setHeader("Accept", "*/*")
                .setHeader("Accept-Language", "en-US,en;q=0.5")
                .setHeader("Accept-Encoding", "gzip, deflate, br")
                .setHeader("Origin", "https://ipwhois.io")
                .setHeader("DNT", "1")
                .setHeader("Referer", "https://ipwhois.io/")
                .setHeader("Sec-Fetch-Dest", "empty")
                .setHeader("Sec-Fetch-Mode", "cors")
                .setHeader("Sec-Fetch-Site", "cross-site")
                .setHeader("sec-ch-ua-platform", "\"Windows\"")
                .setHeader("sec-ch-ua", "\"Google Chrome\";v=\"111\", \"Chromium\";v=\"111\", \"Not=A?Brand\";v=\"24\"")
                .setHeader("sec-ch-ua-mobile", "?0")
                .setHeader("Pragma", "no-cache")
                .setHeader("Cache-Control", "no-cache")
                .build();

        try {
            // Send the request and receive the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response status code is 200 (OK)
            if (response.statusCode() == 200) {
                // Parse the JSON response using Gson
                String responseBody = response.body();
                return JsonParser.parseString(responseBody).getAsJsonObject();
            }
        } catch (IOException | InterruptedException e) {
            // Handle the error as needed, e.g., log it
        }

        // Return null if an error occurs
        return null;
    }
}
