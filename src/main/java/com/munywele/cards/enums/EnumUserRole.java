package com.munywele.cards.enums;

public enum EnumUserRole {

    ADMIN("ROLE_ADMIN"), MEMBER("ROLE_MEMBER");

    private final String roleName;

    EnumUserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
