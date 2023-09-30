package com.paul.billing_system.controller;

import com.paul.billing_system.service.OrgBasedInvestigationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrgBasedInvestigationController {
    private final OrgBasedInvestigationService orgBasedInvestigationService;

    public OrgBasedInvestigationController(OrgBasedInvestigationService orgBasedInvestigationService) {
        this.orgBasedInvestigationService = orgBasedInvestigationService;
    }


}
