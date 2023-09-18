package com.paul.billing_system.enums;

public enum UserRoles {
    ROLE_ROOT("ROLE_ROOT"),
    ROLE_ORG_ADMIN("ROLE_ORG_ADMIN"),
    ROLE_ADMIN("ROLE_ADMIN");
    private final String label;

    UserRoles(String label) {
        this.label = label;
    }

    public static UserRoles getUserRolesByLabel(String label){
        for (UserRoles roles : UserRoles.values()){
            if (roles.label.equals(label))return roles;
        }return null;
    }

    public static String getLabelByUserRoles(UserRoles userRoles){
        return userRoles.label;
    }
}
