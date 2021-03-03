package com.dts.qlnhanvien.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Setter
@Getter
public class EmployeeDTO {

    private String userId;

    private String nameDisplay;

    private String phone;

    private String role;

    private Date birthday;

    private int sex;

    private String email;

    private String address;

    private String facebook;

    private String skype;

    private int status;

    private Date startedDate;

    private Long salary;


}
