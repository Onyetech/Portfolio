package com.example.akpaego.reloadly;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class AccessToken {

        public String getAccessToken() throws java.io.IOException {

                HttpClient httpClient = HttpClientBuilder.create().build();

                HttpPost request = new HttpPost("https://auth.reloadly.com/oauth/token");
                request.setHeader("Content-Type", "application/json");
                JSONObject payload = new JSONObject();
                payload.put("client_id", "NgUEt4yCpwFNFabma1yapp5Q5ro0ynRM");
                payload.put("client_secret", "0vpaxYdTJ8-QWkWxDlO8qWglhHkHV9-Echw1swqYI7xkzvPLKz9WFfkA47LpgNV");
                payload.put("grant_type", "client_credentials");
                payload.put("audience", "https://topups-sandbox.reloadly.com");
                request.setEntity(new StringEntity(payload.toString()));
                String response = EntityUtils.toString(httpClient.execute(request).getEntity());

                System.out.println("Here is a response with the access token "+response);

                return response;
        }

}
