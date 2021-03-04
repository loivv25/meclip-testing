package com.dts.qlnhanvien.service;

import com.dts.qlnhanvien.base.DateUtil;
import com.dts.qlnhanvien.base.Result;
import com.dts.qlnhanvien.common.StringUtl;
import com.dts.qlnhanvien.document.Employee;
import com.dts.qlnhanvien.document.LSDiemDanh;
import com.dts.qlnhanvien.dto.EmployeeDTO;
import com.dts.qlnhanvien.repository.EmployeeRepo;
import com.dts.qlnhanvien.repository.LSDiemDanhRepo;
import com.dts.qlnhanvien.repository.LSDiemDanhRepoCustom;
import com.dts.qlnhanvien.request.LoginRequest;
import com.dts.qlnhanvien.request.SignRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    LSDiemDanhRepoCustom lsDiemDanhRepoCustom;

    @Autowired
    LSDiemDanhRepo lsDiemDanhRepo;

    @Override
    public Result createEmployee(SignRequest signRequest) {
        System.out.println(signRequest.toString());

        if (!StringUtils.hasLength(signRequest.getDisplayName()))
            return Result.fail("Tên không được để trống");
        if (!StringUtils.hasLength(signRequest.getPassword()))
            return Result.fail("Password không được để trống!");
        if (!StringUtils.hasLength(signRequest.getPhone()))
            return Result.fail("Số điện thoại không được để trống!");
        if (!StringUtils.hasLength((signRequest.getEmail())))
            return Result.fail(("Email không được để trống!"));
        if (employeeRepo.existsByPhone(signRequest.getPhone()))
            return Result.fail(("Số điện thoại đã tồn tại!"));
        if (employeeRepo.existsByEmail(signRequest.getEmail()))
            return Result.fail("Email đã tồn tại");

        Employee employee = new Employee();
        employee.setEmail(signRequest.getEmail());
        employee.setPhone(signRequest.getPhone());
        employee.setPassword((DigestUtils.md5DigestAsHex(signRequest.getPassword().getBytes(StandardCharsets.UTF_8))));
        employee.setNameDisplay(signRequest.getDisplayName());
        employee.setStatus(1);
        employee.setAnsiName(StringUtl.getAnsiString(signRequest.getDisplayName()));

        employeeRepo.save(employee);
        return Result.success();
    }


    @Override
    public Result login(LoginRequest request) throws Exception {

        if (!StringUtils.hasLength(request.getPhone()))
            return Result.fail("Số điện thoại không được để trống!");

        Employee emp = employeeRepo.findByPhone(request.getPhone());

        if (emp == null)
            throw new Exception("Không tồn tại nhân viên này!");

        if (!DigestUtils.md5DigestAsHex(request.getPassword().getBytes(StandardCharsets.UTF_8)).equals(emp.getPassword()))
            throw new Exception("Mật khẩu không chính xác");
        else {
            EmployeeDTO employeeDTO = new EmployeeDTO();

            employeeDTO.setUserId(emp.getUserId());
            employeeDTO.setNameDisplay(emp.getNameDisplay());
            employeeDTO.setPhone(emp.getPhone());
            employeeDTO.setEmail(emp.getEmail());
            employeeDTO.setAddress(emp.getAddress());
            employeeDTO.setBirthday(emp.getBirthday());
            employeeDTO.setRole(emp.getRole());
            employeeDTO.setFacebook(emp.getFacebook());
            employeeDTO.setSex(emp.getSex());
            employeeDTO.setSkype(emp.getSkype());
            employeeDTO.setStartedDate(emp.getStartedDate());
            employeeDTO.setSalary(emp.getSalary());

            return Result.success(employeeDTO);
        }
    }

    @Override
    public Result getEmpInfor(String id) {
        Optional<Employee> employeeDTO = employeeRepo.findById(id);
        if (employeeDTO == null)
            return Result.fail("Không tồn tại nhân viên này!");
        return Result.success(employeeDTO);
    }

    @Override
    public Result updateEmp(Employee employee) {

        Optional<Employee> emp = employeeRepo.findById(employee.getId());
        if (!emp.isPresent())
            return Result.fail("Không tồn tại nhân viên này!");
        else {

            if (StringUtils.hasLength(employee.getNameDisplay())) {
                emp.get().setNameDisplay(employee.getNameDisplay());
                emp.get().setAnsiName(emp.get().getNameDisplay());
            }
            if (StringUtils.hasLength((employee.getPhone())))
                emp.get().setPhone(employee.getPhone());

            if (StringUtils.hasLength((employee.getEmail())))
                emp.get().setEmail(employee.getEmail());

            employeeRepo.save(emp.get());
        }
        return Result.success();
    }

    @Override
    public Result checkin(String id) {
        Optional<Employee> emp = employeeRepo.findById(id);
        if (!emp.isPresent())
            return Result.fail("Không tồn tại nhân viên này!");
        else {
            if (lsDiemDanhRepoCustom.checkCheckin(id))
                return Result.fail("Nhân viên đã điểm danh trong hôm nay!");
            else {
                LSDiemDanh lsDiemDanh = new LSDiemDanh();

                lsDiemDanh.setEmployeeId(id);

                Date today = new Date();

                lsDiemDanh.setCheckinTime(new Date());
                lsDiemDanh.setCheckinDay(DateUtil.buildPartDate(new Date().getTime()));
                lsDiemDanh.setName(emp.get().getNameDisplay());
                lsDiemDanhRepo.save(lsDiemDanh);
                return Result.success();
            }
        }

    }
}


