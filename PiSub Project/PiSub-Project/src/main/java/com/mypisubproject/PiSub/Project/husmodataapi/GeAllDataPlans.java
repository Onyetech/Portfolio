package com.mypisubproject.PiSub.Project.husmodataapi;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeAllDataPlans {

    @Value("${husmodataapi.access_token}")
    private String ACCESS_TOKEN;
    private static final Logger logger = LoggerFactory.getLogger(GeAllDataPlans.class);

    public String getAllDataPlans() throws UnirestException {

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://husmodataapi.com/api/network/")
                .header("Authorization", "Token " + ACCESS_TOKEN)
                .header("Content-Type", "application/json")
                .asString();
        logger.info("The response is {}",  response.getBody().toString());
        return response.getBody();
    }

}
