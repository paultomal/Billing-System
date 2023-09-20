package com.paul.billing_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Investigation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String serviceName;

    private Double serviceCharge;

    @ManyToOne
    @JoinTable(
            name = "investigation_specialitist",
            joinColumns = @JoinColumn(name = "investigation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "specialitist_id")
    )
    private Specialist specialist;
}
