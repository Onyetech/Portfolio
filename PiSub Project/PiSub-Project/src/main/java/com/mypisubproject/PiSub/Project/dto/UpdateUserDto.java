package com.mypisubproject.PiSub.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    private String uniqueName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
