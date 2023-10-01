package com.paul.billing_system.entity;

import com.paul.billing_system.enums.DaysOfWeek;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Doctor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String degrees;

    private String contact;

    private String email;

    private String followUp;

    private String consultationFee;

    private String minDiscount;

    private String maxDiscount;

    @OneToMany
    @JoinTable(
            name = "doctor_available_slots",
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_slot_id")
    )
    private List<DoctorSlot> doctorSlots;

    @ManyToMany
    @JoinTable(
            name = "doctor_organization",
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id")
    )
    private List<Organization> organizationList;

    @ManyToMany
    @JoinTable(
            name = "doctor_speciality",
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id")
    )
    private List<Speciality> specialityList;
}
