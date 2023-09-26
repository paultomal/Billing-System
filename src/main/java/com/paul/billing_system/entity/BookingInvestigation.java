package com.paul.billing_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookingInvestigation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "booking_tbl",
            joinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "invastigaion_id")
    )
    private List<Investigation> investigations;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patients patients;
}
