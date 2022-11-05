package com.mypisubproject.PiSub.Project.reloadly;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;

@Component
@TestPropertySource(value = "classpath:application.yml")
public class AirtimeDataTopUpService {

    @Value("${reloadly.access_token}")
    private String ACCESS_TOKEN;

    @Value("${reloadly.airtime-data-baseUrl}")
    private String PAYSTACK_BASE_URL;


    public static void main(String[] args ) throws java.io.IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("https://topups-sandbox.reloadly.com/topups");
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "Bearer eyJraWQiOiI1N2JjZjNhNy01YmYwLTQ1M2QtODQ0Mi03ODhlMTA4OWI3MDIiLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjI5MSIsImlzcyI6Imh0dHBzOi8vcmVsb2FkbHktc2FuZGJveC5hdXRoMC5jb20vIiwiaHR0cHM6Ly9yZWxvYWRseS5jb20vc2FuZGJveCI6dHJ1ZSwiaHR0cHM6Ly9yZWxvYWRseS5jb20vcHJlcGFpZFVzZXJJZCI6IjEyMjkxIiwiZ3R5IjoiY2xpZW50LWNyZWRlbnRpYWxzIiwiYXVkIjoiaHR0cHM6Ly90b3B1cHMtaHMyNTYtc2FuZGJveC5yZWxvYWRseS5jb20iLCJuYmYiOjE2NjczODgzNDMsImF6cCI6IjEyMjkxIiwic2NvcGUiOiJzZW5kLXRvcHVwcyByZWFkLW9wZXJhdG9ycyByZWFkLXByb21vdGlvbnMgcmVhZC10b3B1cHMtaGlzdG9yeSByZWFkLXByZXBhaWQtYmFsYW5jZSByZWFkLXByZXBhaWQtY29tbWlzc2lvbnMiLCJleHAiOjE2Njc0NzQ3NDMsImh0dHBzOi8vcmVsb2FkbHkuY29tL2p0aSI6ImRmMjcyNzg2LTY4YWMtNDNjNi04NTE2LWEyMDIxNzdjYzRhZiIsImlhdCI6MTY2NzM4ODM0MywianRpIjoiOTE3YWYwYWMtMTJlZS00NjZhLWJhNDAtMzAxZWRjZWIwZDVjIn0.AKw653uKtlHo25c_mlN_yAvXb2SlQoslqf6oIW2DpHc");
        request.setHeader("Accept", "application/com.reloadly.topups-v1+json");

        JSONObject payload = new JSONObject();
        payload.put("operatorId", 1100);
        payload.put("amount", 2000);
        payload.put("useLocalAmount", false);
        payload.put("customIdentifier", "hthUIhjkyonh");
        payload.put("recipientEmail", "ikennajf@gmail.com");

        payload.put("recipientPhone", new JSONObject()
                .put("countryCode", "AE")
                .put("number", "0503971821")
        );
        payload.put("senderPhone", new JSONObject()
                .put("countryCode", "CA")
                .put("number", "11231231231")
        );
        request.setEntity(new StringEntity(payload.toString()));

        String response = EntityUtils.toString(httpClient.execute(request).getEntity());

        System.out.println(response);
    }
}
