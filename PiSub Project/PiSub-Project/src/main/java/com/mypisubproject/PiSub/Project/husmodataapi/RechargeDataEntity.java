package com.mypisubproject.PiSub.Project.husmodataapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RechargeDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_no", nullable = false)
    private Long serialNo;

    private int id;  //the network_id
    private int network;
    private String plan_network;
    private String month_validate;
    private String plan;
    private double plan_amount;
    private String ident;
    private double balance_before;
    private double balance_after;
    private String mobile_number;
    private String Status;
    private LocalDateTime create_date = LocalDateTime.now();
    private boolean Ported_number = true;


}
