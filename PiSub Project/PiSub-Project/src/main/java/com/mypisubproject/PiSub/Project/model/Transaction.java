package com.mypisubproject.PiSub.Project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mypisubproject.PiSub.Project.enums.TransactionType;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trasId", nullable = false)
    private Long id;

    private BigDecimal amount;

    private LocalDateTime transTime = LocalDateTime.now();

    private String transRef;
    private String currency;

    private BigDecimal fees;

    private String status;

    @Enumerated(EnumType.STRING)
    private TransactionType tranType;

    private String ip_address;

    private String paid_at;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user" , referencedColumnName = "userId")
    private User user;

}
