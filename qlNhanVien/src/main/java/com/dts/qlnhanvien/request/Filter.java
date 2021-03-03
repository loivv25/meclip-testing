package com.dts.qlnhanvien.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Filter {
    private String name;

    private String phone;

    private String email;

    public Filter(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
