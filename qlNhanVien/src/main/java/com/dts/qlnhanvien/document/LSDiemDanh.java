package com.dts.qlnhanvien.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "ls_diem_danh")
@Setter
@Getter
public class LSDiemDanh extends Base {
    @Field("checkin_id")
    private String checkinId;

    @Field("checkin_time")
    private Date checkinTime;

    private String name;

    @Field("employee_id")
    private String employeeId;



}
