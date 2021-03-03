package com.dts.qlnhanvien.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReportDTO {
    private long totalUser;
    private long activeUser;
    private long nonActiveUser;


    public UserReportDTO() {

    }
}
