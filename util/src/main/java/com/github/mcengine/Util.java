package com.github.mcengine;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class Util {
    public static Properties readPropertiesFile(String filePath) {
        // Create a Properties object
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            // Load the properties from the file
            properties.load(fileInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return the Properties object
        return properties;
    }

    public static String getGitHubLatestReleaseTag(String organization, String repo) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/repos/" + organization + "/" + repo + "/releases/latest"))
                .header("Accept", "application/vnd.github.v3+json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            // Parse JSON response to get the tag name
            String responseBody = response.body();
            int tagStartIndex = responseBody.indexOf("\"tag_name\":") + "\"tag_name\":".length();
            int tagEndIndex = responseBody.indexOf(",", tagStartIndex);
            return responseBody.substring(tagStartIndex, tagEndIndex).replace("\"", "").trim();
        } else {
            throw new IOException("Failed to fetch latest release tag. Status code: " + response.statusCode());
        }
    }
}
