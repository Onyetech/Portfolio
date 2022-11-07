package com.mypisubproject.PiSub.Project.husmodataapi;

import com.example.akpaego.exceptions.BadCallException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MonnifyStyle {

    @Value("${husmodataapi.access_token}")
    private String ACCESS_TOKEN;

    final static Logger logger = LoggerFactory.getLogger(MonnifyStyle.class);
    public DataTopUpResponse dataTopUp(DataTopUpRequest request) throws Exception {

        logger.info("I am in dataTopUp() method in MonnifyStyle");

        try {
            request.setPlan(request.getPlan());
            request.setPorted_number(request.isPorted_number());
            request.setNetwork(request.getNetwork());
            request.setMobile_number(request.getMobile_number());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String url = "https://husmodataapi.com/api/data/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Token " + ACCESS_TOKEN);
//        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        headers.set("Content-Type", "application/json");


        RestTemplate restTemplate = new RestTemplate();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(request);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        ResponseEntity<DataTopUpResponse> responseEntity;
        try {

            responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, DataTopUpResponse.class);
        } catch (Exception ex) {
            throw new RuntimeException();
//            throw new BadCallException("===== .. something wrong with the call. there was no transfer .. =====");

        }
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {

            try {

                logger.info("Status code ============= " + responseEntity.getStatusCode());


            } catch (Exception e) {
                BadCallException badCallException;
                throw new BadCallException("===== something wrong with the call. there was no transfer =====");
            }
        }

        return responseEntity.getBody();
    }
}
