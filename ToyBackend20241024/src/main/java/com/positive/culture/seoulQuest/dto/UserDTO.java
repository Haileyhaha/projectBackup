package com.positive.culture.seoulQuest.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private  Long id;
    private String city;
    private String  state ;
    private String  email ;
    private Boolean social;
    private String street;
    private String zipcode;
    private Date birthday;
    private String memberId;
    private String name;
    private String nickname;
    private String password;
    private String phoneNumber;
}
