package com.example.akpaego.reloadly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SenderPhone {
    private String countryCode;
    private String number;
}
