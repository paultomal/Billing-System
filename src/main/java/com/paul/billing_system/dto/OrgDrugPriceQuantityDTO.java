package com.paul.billing_system.dto;

import com.paul.billing_system.entity.OrgDrugPriceQuantity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgDrugPriceQuantityDTO {
    private Long id;
    private Long orgId;
    private Long drugId;
    private Double price;
    private Long quantity;

    public static OrgDrugPriceQuantityDTO form(OrgDrugPriceQuantity orgDrugPriceQuantity) {
        OrgDrugPriceQuantityDTO orgDrugPriceQuantityDTO = new OrgDrugPriceQuantityDTO();
        orgDrugPriceQuantityDTO.setId(orgDrugPriceQuantity.getId());
        orgDrugPriceQuantityDTO.setOrgId(orgDrugPriceQuantity.getOrganization().getId());
        orgDrugPriceQuantityDTO.setDrugId(orgDrugPriceQuantity.getDrug().getId());
        orgDrugPriceQuantityDTO.setPrice(orgDrugPriceQuantity.getPrice());
        orgDrugPriceQuantityDTO.setQuantity(orgDrugPriceQuantity.getQuantity());

        return orgDrugPriceQuantityDTO;
    }
}
