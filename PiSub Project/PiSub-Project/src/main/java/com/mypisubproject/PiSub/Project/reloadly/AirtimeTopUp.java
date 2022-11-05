package com.mypisubproject.PiSub.Project.reloadly;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Component
@Getter
@Setter
@Entity(name = "AirtimeDataTopUp")
public class AirtimeTopUp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long operatorId;
    private double amount;
    private boolean useLocalAmount;
    private UUID customIdentifier = UUID.randomUUID();
    private String recipientEmail;

    private static RecipientPhone recipientPhone;
    private static SenderPhone senderPhone;
}
