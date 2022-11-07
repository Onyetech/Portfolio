package com.mypisubproject.PiSub.Project.reloadly;

import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AirtimeTopUpRequest {
    private Long operatorId;
    private BigDecimal amount;
    private boolean useLocalAmount;
    private UUID customIdentifier = UUID.randomUUID();
    private String recipientEmail;

    private String countryCode;
    private String number;

    private Object recipientPhone = getRecipientPhone(countryCode, number);
    private Object senderPhone = getSenderPhone(countryCode, number);

    public Object getRecipientPhone(String countryCode, String number) {
        Map<String, Object> recipientPhone = new HashMap<>();
        recipientPhone.put("countryCode", countryCode);
        recipientPhone.put("number", number);

        return recipientPhone;
    }

    public Object getSenderPhone(String countryCode, String number) {
        Map<String, Object> senderPhone = new HashMap<>();
        senderPhone.put("countryCode", countryCode);
        senderPhone.put("number", number);

        return senderPhone;
    }

}
