package com.paul.billing_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppointmentBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "org_id")
    @NotNull
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @NotNull
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @NotNull
    private Doctor doctor;

    private String consultationFee;

    private String discount;

    @NotNull
    private double totalFees;

}
