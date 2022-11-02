package com.mypisubproject.PiSub.Project.paystack;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @Builder
    public class VerifyTransactionResponse {
        private boolean status;
        private String message;
        private Data data;

    }
