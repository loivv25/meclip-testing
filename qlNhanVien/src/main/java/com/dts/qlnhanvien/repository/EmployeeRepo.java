package com.dts.qlnhanvien.repository;

import com.dts.qlnhanvien.document.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepo extends MongoRepository<Employee, String> {
    public boolean existsByPhone(String phone);

    public boolean existsByEmail(String email);

    public Employee findByPhone(String phone);

    public List<Employee> findAllByStatus(int status);
}
