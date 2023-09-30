package com.paul.billing_system.service;

import org.springframework.stereotype.Service;

@Service
public class OrgBasedInvestigationService {
    private final OrgBasedInvestigationService orgBasedInvestigationService;

    public OrgBasedInvestigationService(OrgBasedInvestigationService orgBasedInvestigationService) {
        this.orgBasedInvestigationService = orgBasedInvestigationService;
    }
}
