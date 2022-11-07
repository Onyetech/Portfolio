package com.mypisubproject.PiSub.Project.husmodataapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataTopUpRequest {

     private int id;  //the network_id
     private String plan_network;
     private String month_validate;
     private double plan_amount;
     private String ident;
     private double balance_before;
     private double balance_after;
     private String status;
     private String mobile_number;
     private String plan;
     private int network;
     private boolean Ported_number = true;

}
