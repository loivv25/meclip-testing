package com.dts.qlnhanvien.service;

import com.dts.qlnhanvien.base.Result;
import com.dts.qlnhanvien.document.Employee;
import com.dts.qlnhanvien.dto.EmployeeDTO;
import com.dts.qlnhanvien.dto.UserReportDTO;
import com.dts.qlnhanvien.repository.EmployeeRepo;
import com.dts.qlnhanvien.repository.EmployeeRepoCustom;
import com.dts.qlnhanvien.repository.LSDiemDanhRepoCustom;
import com.dts.qlnhanvien.request.Filter;
import com.dts.qlnhanvien.request.SignRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    LSDiemDanhRepoCustom lsDiemDanhRepoCustom;

    @Autowired
    EmployeeRepoCustom employeeRepoCustom;

    @Override
    public Result approveEmp(List<String> listEmpId) {
        System.out.println(listEmpId.toString());

        listEmpId.forEach((element) -> {
                    Optional<Employee> employee = employeeRepo.findById(element);
                    Employee approEmp = employee.get();
                    approEmp.setStatus(0);
                    employeeRepo.save(approEmp);
                }
        );
        return Result.success();
    }

    @Override
    public Result getListCheckin(int start, int limit) {
        System.out.println("Start:" + start + "limit:" + limit);

        return Result.success(lsDiemDanhRepoCustom.getListCheckInToday(start, limit));
    }

    @Override
    public Result filter(Filter filter) {
        System.out.println(filter.toString());
        List<Employee> listEmp = employeeRepoCustom.filter(filter);
        List<EmployeeDTO> listEmpDTO = null;
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
        System.out.println(keyword);
        return Result.success(employeeRepoCustom.search(keyword));
    }

    @Override
    public Result deleteUser(String id) {
        System.out.println(id);
        if (!employeeRepo.existsById(id))
            return Result.fail("Không tồn tại nhân viên này");
        employeeRepo.deleteById(id);
        return Result.success();

    }

    @Override
    public Result deleteListUser(List<String> listEmpId) {
        System.out.println(listEmpId);
        listEmpId.forEach((element) ->
                deleteUser(element));
        return Result.success();
    }

    @Override
    public Result createNewUser(SignRequest signRequest) {
        System.out.println(signRequest);

        if (!StringUtils.hasLength(signRequest.getDisplayName()))
            return Result.fail("Tên không được để trống");
        if (!StringUtils.hasLength(signRequest.getPhone()))
            return Result.fail("Số điện thoại không được để trống!");
        if (!StringUtils.hasLength((signRequest.getEmail())))
            return Result.fail(("Email không được để trống!"));
        if (employeeRepo.existsByPhone(signRequest.getPhone()))
            return Result.fail(("Số điện thoại đã tồn tại!"));
        if (employeeRepo.existsByEmail(signRequest.getEmail()))
            return Result.fail("Email đã tồn tại");
        if (!StringUtils.hasLength(signRequest.getPassword()))
            signRequest.setPassword("123");

        Employee employee = new Employee();
        employee.setEmail(signRequest.getEmail());
        employee.setPhone(signRequest.getPhone());
        employee.setPassword((DigestUtils.md5DigestAsHex(signRequest.getPassword().getBytes(StandardCharsets.UTF_8))));
        employee.setName_display(signRequest.getDisplayName());
        employee.setStatus(1);

        employeeRepo.save(employee);
        return Result.success();
    }

    @Override
    public Result createNewListUser(List<SignRequest> signRequestList) {
        System.out.println(signRequestList);
        signRequestList.forEach((signRequest) ->
                createNewUser(signRequest));
        return Result.success();
    }

    @Override
    public Result searchById(String id) {
        System.out.println(id);

        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee == null)
            return Result.fail("Không tồn tại nhân viên này");
        Employee emp = employee.get();
        EmployeeDTO empDTO = new EmployeeDTO();
        empDTO.setEmail(emp.getEmail());
        empDTO.setPhone(emp.getPhone());
        empDTO.setAddress(emp.getFacebook());
        empDTO.setNameDisplay(emp.getName_display());
        empDTO.setSex(empDTO.getSex());
        empDTO.setBirthday(emp.getBirthday());
        empDTO.setSkype(emp.getSkype());
        empDTO.setStartedDate(emp.getStartedDate());
        return Result.success();

    }

    @Override
    public Result searchCheckin(Date startDate, Date endDate, int start, int limit) {
        System.out.println("startDate:" + startDate + "endDate:" + endDate + "start:" + start + "limit:" + limit);
        Result.success(lsDiemDanhRepoCustom.searchCheckIn(startDate, endDate, start, limit));
        return null;
    }

    @Override
    public Result searchGt10M() {

        return Result.success(employeeRepoCustom.searchGt10M());
    }

    @Override
    public Result searchLt10M() {
        return Result.success(employeeRepoCustom.searchLt10M());
    }

    @Override
    public Result searchGte10M() {
        return Result.success(employeeRepoCustom.searchGte10M());
    }

    @Override
    public Result searchLte10M() {
        return Result.success(employeeRepoCustom.searchLt10M());
    }

    @Override
    public Result searchEqual10M() {
        return Result.success(employeeRepoCustom.searchEqual10M());
    }

    @Override
    public Result searchIn10Mto20() {
        return Result.success(employeeRepoCustom.searchBtw10Mand20M());
    }

    @Override
    public Result searchNonIn10M() {
        return Result.success(employeeRepoCustom.searchNoneIn10M());
    }

    @Override
    public Result reportUserStatus() {
        UserReportDTO userReportDTO = new UserReportDTO();
        userReportDTO.setTotalUser(employeeRepo.findAll().stream().count());
        userReportDTO.setActiveUser(employeeRepo.findAllByStatus(0).size());
        userReportDTO.setNonActiveUser(employeeRepo.findAllByStatus(1).size());

        return Result.success(userReportDTO);
    }

    @Override
    public Result changeUserStatus(String id, int status) {
        System.out.println("id:" + id + "status:" + status);

        Optional<Employee> emp = employeeRepo.findById(id);
        if (emp == null)
            return Result.fail("Không tồn tại nhân viên này!");
        else {
            Employee emp1 = emp.get();
            emp1.setStatus(status);
            employeeRepo.save(emp1);
        }
        return Result.success();
    }

    @Override
    public Result updateUserInfor(Employee employee) {
        System.out.println(employee);
        Optional<Employee> emp = employeeRepo.findById(employee.getUserId());
        if (emp == null)
            return Result.fail("Không tồn tại nhân viên này!");
        else employeeRepo.save(employee);
        return Result.success();
    }
}
