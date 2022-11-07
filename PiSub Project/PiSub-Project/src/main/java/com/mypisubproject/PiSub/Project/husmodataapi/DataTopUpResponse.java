package com.mypisubproject.PiSub.Project.husmodataapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataTopUpResponse {

    private String id;  //the network_id
    private int network;
    private String api_response;
    private String ident;
    private double balance_before;
    private double balance_after;
    private String mobile_number;
    private int plan;
    private String status;
    private String plan_network;
    private String plan_name;
    private double plan_amount;
    private String create_date;
    private boolean Ported_number;

}
