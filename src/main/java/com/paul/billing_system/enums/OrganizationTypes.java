package com.paul.billing_system.enums;

import java.util.ArrayList;
import java.util.List;

public enum OrganizationTypes {
    HOSPITAL("Hospital"),
    CLINIC("Clinic"),
    PHARMACEUTICAL("Pharmaceutical");
    private final String label;
    OrganizationTypes (String label) {
        this.label = label;
    }
    public static String getLabelByGrade(OrganizationTypes organizationTypes) {
        return organizationTypes.label;
    }
    public static OrganizationTypes getOrganizationTypeByLabel(String label) {
        for (OrganizationTypes types : OrganizationTypes.values()) {
            if(types.label.equals(label)) return types;
        }
        return null;
    }
    public static String getLabelByOrganizationType(OrganizationTypes organizationTypes){
        return organizationTypes.label;
    }
    public static List<String> getAllOrganizationTypesList() {
        List<String> organizationTypesList = new ArrayList<>();
        for (OrganizationTypes organizationTypes: OrganizationTypes.values()) {
            organizationTypesList.add(OrganizationTypes.getLabelByGrade(organizationTypes));
        }
        return organizationTypesList;
    }
}