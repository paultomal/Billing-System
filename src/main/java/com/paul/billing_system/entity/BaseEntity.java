package com.paul.billing_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    private Date createdAt;
    private Date updatedAt;
    private UserInfo updatedBy;
    private UserInfo createdBy;
}
