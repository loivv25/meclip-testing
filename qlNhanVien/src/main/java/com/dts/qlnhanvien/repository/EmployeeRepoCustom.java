package com.dts.qlnhanvien.repository;

import com.dts.qlnhanvien.base.Result;
import com.dts.qlnhanvien.common.StringUtl;
import com.dts.qlnhanvien.document.Employee;
import com.dts.qlnhanvien.request.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class EmployeeRepoCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    EmployeeRepo employeeRepo;

    public List<Employee> filter(Filter filter) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (!StringUtils.hasLength(filter.getName()))
            criteria.and("displayName").is(filter.getName().trim());
        if (!StringUtils.hasLength((filter.getEmail())))
            criteria.and("email").is(filter.getEmail().trim());
        if (!StringUtils.hasLength(filter.getPhone()))
            criteria.and(("phone")).is(filter.getPhone());

        return mongoTemplate.find(query, Employee.class);
    }

    public List<Employee> search(String keyword) {

        String ansiKey = StringUtl.getAnsiString(keyword);

        Query queryName = new Query();
        Criteria criteriaName = new Criteria();
        criteriaName.and("ansiName").is(ansiKey);
        List<Employee> employeeListbyName = mongoTemplate.find(queryName, Employee.class);

        Query queryPhone = new Query();
        Criteria criteriaPhone = new Criteria();
        criteriaName.and("phone").is(ansiKey);
        List<Employee> employeeListbyPhone = mongoTemplate.find(queryPhone, Employee.class);

        Query queryEmail = new Query();
        Criteria criteriaEmail = new Criteria();
        criteriaName.and("ansiName").is(ansiKey);
        List<Employee> employeeListbyEmail = mongoTemplate.find(queryEmail, Employee.class);

        employeeListbyName.forEach((element) -> {
            employeeListbyEmail.add(element);
        });

        employeeListbyPhone.forEach((element) ->
                employeeListbyEmail.add(element));

        return employeeListbyEmail;
    }

    public List<Employee> searchGt10M() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("salary").gt(10000000);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Employee.class);
    }

    public List<Employee> searchLt10M() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("salary").lt(10000000);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Employee.class);
    }

    public List<Employee> searchGte10M() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("salary").gte(10000000);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Employee.class);
    }

    public List<Employee> searchLte10M() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("salary").gte(10000000);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Employee.class);
    }

    public List<Employee> searchEqual10M() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("salary").equals(10000000);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Employee.class);
    }

    public List<Employee> searchNoneIn10M() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("salary").nin(10000000);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Employee.class);
    }

    public List<Employee> searchBtw10Mand20M() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("salary").gte(10000000);
        criteria.and("salary").lte(20000000);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Employee.class);
    }


}
