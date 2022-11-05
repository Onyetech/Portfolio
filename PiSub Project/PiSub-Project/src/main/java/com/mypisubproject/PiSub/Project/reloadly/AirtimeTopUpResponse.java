package com.mypisubproject.PiSub.Project.reloadly;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AirtimeTopUpResponse {

    private int transactionId;
    private String status;
    private int operatorTransactionId;
    private UUID customIdentifier = UUID.randomUUID();
    private String recipientPhone;
    private String recipientEmail;
    private String senderPhone;
    private String countryCode;
    private int operatorId;
    private String operatorName;
    private double discount;
    private String discountCurrencyCode;
    private int requestedAmount;
    private String requestedAmountCurrencyCode;
    private double deliveredAmount;
    private String deliveredAmountCurrencyCode;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionDate  = LocalDateTime.now();

    private String pinDetail;
    private BalanceInfo balanceInfo;

}

