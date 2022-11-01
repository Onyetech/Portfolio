package com.mypisubproject.PiSub.Project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "userId", nullable = false)
    private int UserId;

    @Column(nullable = false, unique = false, length = 30)
    private String uniqueName;

    @Column(nullable = true, unique = false, length = 30)
    private String firstName;

    @Column(nullable = true, length = 30)
    private String lastName;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = true, length = 100)
    private String phoneNumber;

    @Column(nullable = true, length = 10)
    private String userRole;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet", referencedColumnName = "walletId")
    private Wallet wallet;

}
