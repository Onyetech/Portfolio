package com.mypisubproject.PiSub.Project.paystack;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public  class Data {
    private BigDecimal amount;
    private String currency;
    private LocalDateTime transaction_date = LocalDateTime.now();
    private String status;
    private String reference;
    private String domain;
    private String gateway_response;
    private String message;
    private String channel;
    private String ip_address;
    private BigDecimal fees;
    private String plan;
    private String paid_at;

}


