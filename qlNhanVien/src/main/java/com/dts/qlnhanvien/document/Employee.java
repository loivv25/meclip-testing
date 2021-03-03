package com.dts.qlnhanvien.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.annotation.Documented;
import java.util.Date;

@Document(
        collection = "nhan_vien"
)
@Setter
@Getter
public class Employee extends Base{

    @Field("user_id")
    private String userId;

    @Field("display_name")
    private String name_display;

    private String phone;

    private String role;

    private Date birthday;

    private int sex;

    private String email;

    private String address;

    private String facebook;

    private String skype;

    private int status;

    @Field("started_date")
    private Date startedDate;

    private Long salary;

    private String password;

    @Field("access_token")
    private String accessToken;

    @Field("refresh_token")
    private String refreshToken;

    @Field("ansi_name")
    private String ansiName;
}
