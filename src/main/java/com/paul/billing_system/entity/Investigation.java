package com.paul.billing_system.entity;

import com.paul.billing_system.dto.InvestigationDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Investigation  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String serviceName;

    private Double serviceCharge;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    private Organization organization;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sp_id")
    private Specialist specialist;

    public static Investigation form(InvestigationDTO investigationDTO) {
        Investigation investigation = new Investigation();
        investigation.setServiceName(investigationDTO.getServiceName());
        investigation.setServiceCharge(investigationDTO.getServiceCharge());
        return investigation;
    }
}
