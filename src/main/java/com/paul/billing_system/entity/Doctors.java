package com.paul.billing_system.entity;

import com.paul.billing_system.enums.DaysOfWeek;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String degrees;

    private String contact;

    private String email;

    private String followUp;

    private String consultation;

    private String minDiscount;

    private String maxDiscount;

    @Enumerated(EnumType.STRING)
    private DaysOfWeek day;

    private String time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    private Organization organization;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sp_id")
    private Specialist specialist;
}
