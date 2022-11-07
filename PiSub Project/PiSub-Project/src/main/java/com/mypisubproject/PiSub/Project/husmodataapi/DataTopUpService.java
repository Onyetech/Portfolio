package com.mypisubproject.PiSub.Project.husmodataapi;

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
public class DataTopUpService {

    @Value("${husmodataapi.base_url}")
    private String BASE_URL;

    @Value("${husmodataapi.access_token}")
    private String API_KEY;

//    @Autowired
//    private UserRepository userRepository;

    public DataTopUpResponse dataTopUp(DataTopUpRequest request) throws Exception {
        System.out.println("Inside dataTopUp service 11 ");

        DataTopUpResponse paymentResponse;
        try {
            System.out.println("Inside try code block");
            Gson gson = new Gson();
            StringEntity entity = new StringEntity(gson.toJson(request));
            System.out.println(entity);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(BASE_URL);
            System.out.println("the post ==== " + post);
            post.setEntity(entity);
            System.out.println("After post dot set entity");

            post.addHeader("Content-type", "application/json");
            post.addHeader("Authorization", "Token " + API_KEY);
            System.out.println("after adding headers .... ");

            StringBuilder result = new StringBuilder();
            HttpResponse response = httpClient.execute(post);
            System.out.println("This is the response 1 " + response);

            if (response.getStatusLine().getStatusCode() == 201) {

                System.out.println("The request is ok, has 200 response code...");

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
                paymentResponse = mapper.readValue(result.toString(), DataTopUpResponse.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException("error occurred while initializing transaction");
        }
        return paymentResponse;
    }
}
