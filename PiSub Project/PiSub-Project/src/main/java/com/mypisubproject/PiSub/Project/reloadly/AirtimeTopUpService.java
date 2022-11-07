package com.mypisubproject.PiSub.Project.reloadly;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mypisubproject.PiSub.Project.model.AirtimeTopUp;
import com.mypisubproject.PiSub.Project.model.Transaction;
import com.mypisubproject.PiSub.Project.model.User;
import com.mypisubproject.PiSub.Project.repository.AirtimeTopUpRepository;
import com.mypisubproject.PiSub.Project.repository.TransactionRepository;
import com.mypisubproject.PiSub.Project.repository.UserRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Service
public class AirtimeTopUpService {
    @Value("${reloadly.access_token}")
    private String ACCESS_TOKEN;
    @Value("${reloadly.airtime-data-baseUrl}")
    private String RELOADLY_BASE_URL;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AirtimeTopUpRepository airtimeTopUpRepository;

    public AirtimeTopUpResponse topUp(AirtimeTopUpRequest request) throws Exception {
        User user = new User();
        User foundByUserId = userRepository.findById(user.getId());

        AirtimeTopUpResponse paymentResponse;
        AirtimeTopUp airtimeTopUp = new AirtimeTopUp();
        try {
            Gson gson = new Gson();
            StringEntity entity = new StringEntity(gson.toJson(request));
            System.out.println(entity);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(RELOADLY_BASE_URL);
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
            throw new RuntimeException("Error occurred while initializing transaction");
        }

        // ADD VALUE

        if (paymentResponse.getStatus().equals("SUCCESSFUL") || paymentResponse.getRecipientPhone() != null){

            BigDecimal transAmount = request.getAmount();
            BigDecimal balance = user.getWallet().getBalance();

            if (transAmount.compareTo(balance) == -1 ){

                BigDecimal newBalance = balance.min(transAmount);
                foundByUserId.getWallet().setBalance(newBalance);

                try {
                    airtimeTopUp.setAmount(request.getAmount());
                    airtimeTopUp.setCustomIdentifier(paymentResponse.getCustomIdentifier());
                    airtimeTopUp.setOperatorId((long) paymentResponse.getOperatorId());
                    airtimeTopUp.setRecipientEmail(paymentResponse.getRecipientEmail());
                    airtimeTopUp.setUseLocalAmount(request.isUseLocalAmount());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else throw new RuntimeException("Your balance is insufficient");

            airtimeTopUpRepository.save(airtimeTopUp);

        }
        return paymentResponse;
    }
}
