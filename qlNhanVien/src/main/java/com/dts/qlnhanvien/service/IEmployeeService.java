package com.dts.qlnhanvien.service;

import com.dts.qlnhanvien.base.Result;
import com.dts.qlnhanvien.document.Employee;
import com.dts.qlnhanvien.request.LoginRequest;
import com.dts.qlnhanvien.request.SignRequest;

public interface IEmployeeService {
    public  Result createEmployee(SignRequest signRequest);
    public Result login(LoginRequest loginRequest) throws Exception;
    public Result getEmpInfor(String id);
    public Result updateEmp(Employee employee);
    public Result checkin(String id);

}
