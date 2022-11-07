package com.mypisubproject.PiSub.Project.model;

import com.mypisubproject.PiSub.Project.reloadly.RecipientPhone;
import com.mypisubproject.PiSub.Project.reloadly.SenderPhone;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Component
@Getter
@Setter
@Entity(name = "AirtimeDataTopUp")
public class AirtimeTopUp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    private Long operatorId;
    private BigDecimal amount;
    private boolean useLocalAmount;
    private UUID customIdentifier = UUID.randomUUID();
    private String recipientEmail;

    private static RecipientPhone recipientPhone;
    private static SenderPhone senderPhone;
}
