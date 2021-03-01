package com.dts.meclip.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Rin
 */

@Getter
@AllArgsConstructor
public enum ResultEnum {
    UNAUTHORIZED(401, "OK"),

    SUCCESS(200, "OK"),

    TOKEN_EXPIRED(301, "Token expired"), TOKEN_ERROR(302, "Token Error"),

    LOGIN_ERROR(303, "Login Error"), REMOTE_ERROR(304, "Remote Error"),

    ITEM_EXIST_ERROR(305, "ITEM_EXIST_ERROR"),

    ITEM_NOT_FOUND_ERROR(306, "ITEM_NOT_FOUND_ERROR"),

    PARAM_INVALID_ERROR(307, "PARAM_INVALID_ERROR"),

    OTP_UNAUTHEN(308, "OTP_UNAUTHEN"),

    PACKAGE_EXPIRED_ERROR(309, "PACKAGE_EXPIRED_ERROR"),

    NO_REG(310, "NO_REG"),

    FAIL(311, "FAIL"),

    SERVICE_ABNORMAL(99, "Dịch vụ đang bận"),

    OUT_OFF_MEMORY(312, "Hết tài nguyên hệ thống"),

    ACCOUNT_NOT_FOUND(501, "Tài khoản không có trong hệ sinh thái"),

    ERROR(999, "Error");

    private Integer code;

    private String desc;
}
