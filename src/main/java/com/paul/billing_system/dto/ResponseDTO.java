package com.paul.billing_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private String token;
    private Date expiredDate;
    private String username;
    private List roles;
    private String orgCode;
    private long orgId;
    private String orgType;
}
