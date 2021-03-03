package com.dts.qlnhanvien.service;

import com.dts.qlnhanvien.base.Result;
import com.dts.qlnhanvien.document.Employee;
import com.dts.qlnhanvien.dto.EmployeeDTO;
import com.dts.qlnhanvien.repository.EmployeeRepo;
import com.dts.qlnhanvien.repository.EmployeeRepoCustom;
import com.dts.qlnhanvien.repository.LSDiemDanhRepoCustom;
import com.dts.qlnhanvien.request.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService{

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    LSDiemDanhRepoCustom lsDiemDanhRepoCustom;

    @Autowired
    EmployeeRepoCustom employeeRepoCustom;
    @Override
    public Result approveEmp(List<String> listEmpId) {
        listEmpId.forEach((element) -> {
                    Optional<Employee> employee = employeeRepo.findById(element);
                    Employee approEmp =  employee.get();
                    approEmp.setStatus(0);
                    employeeRepo.save(approEmp);
                }
        );
        return Result.success();
    }

    @Override
    public Result getListCheckin(int start, int limit) {
        return  Result.success(lsDiemDanhRepoCustom.getListCheckInToday(start, limit));
    }

    @Override
    public Result filter(Filter filter) {
        List<Employee> listEmp = employeeRepoCustom.filter(filter);
        List<EmployeeDTO> listEmpDTO=null;
        listEmp.forEach((emp) ->
                {
                    EmployeeDTO empDTO = new EmployeeDTO();
                    empDTO.setEmail(emp.getEmail());
                    empDTO.setPhone(emp.getPhone());
                    empDTO.setAddress(emp.getFacebook());
                    empDTO.setNameDisplay(emp.getName_display());
                    empDTO.setSex(empDTO.getSex());
                    empDTO.setBirthday(emp.getBirthday());
                    empDTO.setSkype(emp.getSkype());
                    empDTO.setStartedDate(emp.getStartedDate());

                    listEmpDTO.add(empDTO);
                }
        );

        return Result.success(listEmpDTO);
    }

    @Override
    public Result search(String keyword) {

        return null;
    }

    @Override
    public Result deleteUser(String id) {

        return null;
    }

    @Override
    public Result deleteListUser(List<Employee> listEmp) {
        return null;
    }

    @Override
    public Result createNewUser(Employee employee) {
        return null;
    }

    @Override
    public Result createNewListUser(List<Employee> employees) {
        return null;
    }

    @Override
    public Result searchById(String id) {
        return null;
    }

    @Override
    public Result searchCheckin() {
        return null;
    }

    @Override
    public Result reportUserStatus() {
        return null;
    }

    @Override
    public Result changeUserStatus(String id) {
        return null;
    }

    @Override
    public Result updateUserInfor(Employee employee) {
        return null;
    }
}
