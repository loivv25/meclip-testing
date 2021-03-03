package com.dts.qlnhanvien.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Setter
@Getter
public class Base {

    @Id
    private String id;
    @Field("created_date")
    private Date createDate;
    @Field("updated_date")
    private Date updateDate;
    @Field("created_by")
    private String createdBy;
    @Field("updated_by")
    private String updateBy;
    private boolean delete;
}
