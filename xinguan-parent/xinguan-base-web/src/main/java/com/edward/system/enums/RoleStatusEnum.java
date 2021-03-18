package com.edward.system.enums;

/**
 * 权限状态
 */
public enum RoleStatusEnum {
    DISABLE(0),
    AVAILABLE(1)
    ;

    private int statusCode;

    RoleStatusEnum(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
