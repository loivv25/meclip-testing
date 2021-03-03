package com.dts.qlnhanvien.api;

import com.dts.qlnhanvien.base.Result;
import com.dts.qlnhanvien.request.Filter;
import com.dts.qlnhanvien.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminApi {

    @Autowired
    AdminService adminService;
    @PostMapping("/approve-list-emp")
    public Result approveListEmployee(@RequestBody List listEmpId){
        adminService.approveEmp(listEmpId);
        return Result.success();
    }

    @GetMapping("/get-list-checkin-today")
    public Result getListCheckinToday(@RequestParam int start, @RequestParam int limit){
        return adminService.getListCheckin(start, limit);
    }

    @GetMapping("/filter")
    public Result filter(@RequestParam String name, @RequestParam String phone, @RequestParam String email){
        Filter filter = new Filter(name, phone, email);
        return adminService.filter(filter);
    }

}
