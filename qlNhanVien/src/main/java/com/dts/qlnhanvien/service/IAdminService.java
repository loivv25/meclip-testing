package com.dts.qlnhanvien.service;

import com.dts.qlnhanvien.base.Result;
import com.dts.qlnhanvien.document.Employee;
import com.dts.qlnhanvien.request.Filter;

import java.util.List;

public interface IAdminService {
    public Result approveEmp(List<String> listEmpId);
    public Result getListCheckin(int start, int limit);
    public Result filter(Filter filter);
    public Result search(String keyword);//chua lam

    public Result deleteUser(String id);
    public Result deleteListUser(List<Employee> listEmp);
    public Result createNewUser(Employee employee);
    public Result createNewListUser(List<Employee> employees);
    public Result searchById(String id);
    public Result searchCheckin();

    // 4.6 to 5.2

    public Result reportUserStatus();
    public Result changeUserStatus(String id);
    public Result updateUserInfor(Employee employee);


}
