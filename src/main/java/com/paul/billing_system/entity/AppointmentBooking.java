package com.paul.billing_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @NotEmpty
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @NotEmpty
    private Doctor doctor;

    private String consultationFee;

    private String discount;

    @NotNull
    private double totalFees;

}
