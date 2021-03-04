package com.dts.qlnhanvien.api;

import com.dts.qlnhanvien.base.Result;
import com.dts.qlnhanvien.document.Employee;
import com.dts.qlnhanvien.request.Filter;
import com.dts.qlnhanvien.request.SignRequest;
import com.dts.qlnhanvien.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminApi {

    @Autowired
    AdminService adminService;

    @PostMapping("/approve-emp")
    public Result approveEmployee(@RequestParam String id,
                                  @RequestParam int status) {
        return adminService.approveEmp(id, status);
    }

    @GetMapping("/get-list-checkin-today")
    public Result getListCheckinToday(@RequestParam int start,
                                      @RequestParam int limit) {
        return adminService.getListCheckin(start, limit);
    }

    @GetMapping("/filter")
    public Result filter(@RequestParam String name,
                         @RequestParam String phone,
                         @RequestParam String email) {
        Filter filter = new Filter(name, phone, email);
        return adminService.filter(filter);
    }

    @GetMapping("/search")
    public Result search(@RequestParam String keyword) {
        return adminService.search(keyword);
    }

    @GetMapping("/search-by-list-id")
    public Result searchByListId(@RequestBody List<String> ListId) {
        return adminService.searchByListId(ListId);
    }

    @PostMapping("/delete")
    public Result deleteUser(@RequestParam String id) {
        return adminService.deleteUser(id);
    }

    @PostMapping("/delete-list-user")
    public Result deleteListUser(@RequestBody List<String> listUserId) {
        return adminService.deleteListUser(listUserId);
    }

    @PostMapping("/create-user")
    public Result createUser(@RequestBody SignRequest signRequest) {
        return adminService.createNewUser(signRequest);
    }

    @PostMapping("/create-list-user")
    public Result createListUser(@RequestBody List<SignRequest> signRequestList) {
        return adminService.createNewListUser(signRequestList);
    }

    @GetMapping("/get-user")
    public Result searchById(@RequestParam String id) {
        return adminService.searchById(id);
    }

    @GetMapping("/search-checkin-by-date")
    public Result searchCheckinByDate(@RequestParam String startDate,
                                      @RequestParam String endDate,
                                      @RequestParam int start,
                                      @RequestParam int limit) {
        return adminService.searchCheckin(startDate, endDate, start, limit);
    }

    @GetMapping("/search-Emp-Gt-10M")
    public Result searchEmpGt10M() {
        return adminService.searchGt10M();
    }

    @GetMapping("/search-Emp-Lt-10M")
    public Result searchEmpLt10M() {
        return adminService.searchLt10M();
    }

    @GetMapping("/search-Emp-Gte-10M")
    public Result searchEmpGte10M() {
        return adminService.searchGte10M();
    }

    @GetMapping("/search-Emp-equal-10M")
    public Result searchEqual10M() {
        return adminService.searchEqual10M();
    }

    @GetMapping("/search-Emp-Lte-10M")
    public Result searchEmpLte10M() {
        return adminService.searchLte10M();
    }

    @GetMapping("/search-Emp-in-10M-to-20M")
    public Result searchEmpIn10Mto20M() {
        return adminService.searchIn10Mto20();
    }

    @GetMapping("/search-Emp-none-in-10M")
    public Result searchEmpNoneIn10M() {
        return adminService.searchNonIn10M();
    }

    @GetMapping("/user-status-report")
    public Result reportUser() {
        return adminService.reportUserStatus();
    }

    @PostMapping("/update-user-status")
    public Result updateUserStatus(@RequestParam String id,
                                   @RequestParam int status) {
        return adminService.changeUserStatus(id, status);
    }

    @PutMapping("/update-user")
    public Result updateUser(@RequestBody Employee employee) {
        return adminService.updateUserInfor(employee);
    }

}
