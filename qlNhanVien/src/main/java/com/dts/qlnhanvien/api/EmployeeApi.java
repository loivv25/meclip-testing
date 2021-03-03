package com.dts.qlnhanvien.api;

import com.dts.qlnhanvien.base.Result;
import com.dts.qlnhanvien.document.Employee;
import com.dts.qlnhanvien.dto.EmployeeDTO;
import com.dts.qlnhanvien.request.LoginRequest;
import com.dts.qlnhanvien.request.SignRequest;
import com.dts.qlnhanvien.service.EmployeeService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emp")
public class EmployeeApi {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/create-emp")
    public Result createEmployee(@RequestBody SignRequest signRequest) {
        return employeeService.createEmployee(signRequest);
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest) throws Exception {
        return employeeService.login(loginRequest);
    }

    @GetMapping("/get-emp-infor")
    public Result getEmpInfor(@PathVariable("id") String id) {
        return employeeService.getEmpInfor(id);
    }

    @PutMapping("/update-emp")
    public Result updateEmp(@RequestBody Employee employee) {
        return employeeService.updateEmp(employee);
    }

    @PostMapping("/checkin")
    public Result checkin(@RequestParam String id) {
        return employeeService.checkin(id);
    }
}
