package com.dts.qlnhanvien.repository;

import com.dts.qlnhanvien.document.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends MongoRepository<Employee, String> {
    public boolean existsByPhone(String phone);

    public boolean existsByEmail(String email);

    public Employee findByPhone(String phone);

    public Employee findByUserId(String id);

    public List<Employee> findAllByStatus(int status);

    public List<Employee> findByIdIn(List<String> ids);
}
