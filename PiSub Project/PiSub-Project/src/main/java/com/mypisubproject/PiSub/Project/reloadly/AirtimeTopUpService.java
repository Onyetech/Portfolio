package com.mypisubproject.PiSub.Project.reloadly;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Service
public class AirtimeTopUpService {
    @Value("${reloadly.access_token}")
    private String ACCESS_TOKEN;
    @Value("${reloadly.airtime-data-baseUrl}")
    private String PAYSTACK_BASE_URL;

    public AirtimeTopUpResponse topUp(AirtimeTopUpRequest request) throws Exception {

        AirtimeTopUpResponse paymentResponse;
        try {
            Gson gson = new Gson();
            StringEntity entity = new StringEntity(gson.toJson(request));
            System.out.println(entity);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(PAYSTACK_BASE_URL);
            System.out.println("the post ==== " + post);
            post.setEntity(entity);

            post.addHeader("Content-type", "application/json");
            post.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
            post.addHeader("Accept", "application/com.reloadly.topups-v1+json");

            StringBuilder result = new StringBuilder();
            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {


                System.out.println("The request is ok, having 200 response code...");

                BufferedReader responseReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = responseReader.readLine()) != null) {
                    System.out.println(line);
                    result.append(line);
                }
            } else {
                throw new AuthenticationException("Error Occurred while initializing transaction");
            }
            try {
                ObjectMapper mapper = new ObjectMapper();
                paymentResponse = mapper.readValue(result.toString(), AirtimeTopUpResponse.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException("error occurred while initializing transaction");
        }


        return paymentResponse;
    }
}
