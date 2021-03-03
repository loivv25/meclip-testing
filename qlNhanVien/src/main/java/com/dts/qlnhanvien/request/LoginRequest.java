package com.dts.qlnhanvien.request;

import com.dts.qlnhanvien.base.Result;
import com.dts.qlnhanvien.repository.EmployeeRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Setter
@Getter
public class LoginRequest {

    private String phone;

    private String password;


}
