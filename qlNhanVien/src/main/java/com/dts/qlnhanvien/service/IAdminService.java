package com.dts.qlnhanvien.service;

import com.dts.qlnhanvien.base.Result;
import com.dts.qlnhanvien.document.Employee;
import com.dts.qlnhanvien.request.Filter;
import com.dts.qlnhanvien.request.SignRequest;

import java.util.Date;
import java.util.List;

public interface IAdminService {
    public Result approveEmp(String id, int status);

    public Result getListCheckin(int start, int limit);

    public Result filter(Filter filter);

    public Result search(String keyword);

    public Result deleteUser(String id);

    public Result deleteListUser(List<String> listEmpId);

    public Result createNewUser(SignRequest signRequest);

    public Result createNewListUser(List<SignRequest> listSignRequests);

    public Result searchById(String id);

    public Result searchCheckin(String startDate, String endDate, int start, int limit);

    // 4.6 to 5.2
    public Result searchGt10M();

    public Result searchLt10M();

    public Result searchGte10M();

    public Result searchLte10M();

    public Result searchEqual10M();

    public Result searchIn10Mto20();

    public Result searchNonIn10M();

    public Result reportUserStatus();

    public Result changeUserStatus(String id, int status);

    public Result updateUserInfor(Employee employee);//


}
