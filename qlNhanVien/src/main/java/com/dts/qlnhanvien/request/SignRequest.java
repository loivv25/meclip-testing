package com.dts.qlnhanvien.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignRequest {
    private String phone;

    private String displayName;

    private String email;

    private String password;
}
