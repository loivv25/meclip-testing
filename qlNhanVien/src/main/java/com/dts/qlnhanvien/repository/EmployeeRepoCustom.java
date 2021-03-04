package com.dts.qlnhanvien.repository;

import com.dts.qlnhanvien.base.Result;
import com.dts.qlnhanvien.common.StringUtl;
import com.dts.qlnhanvien.document.Employee;
import com.dts.qlnhanvien.dto.UserReportDTO;
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

        if (StringUtils.hasLength(filter.getName()))
            criteria.and("nameDisplay").is(filter.getName().trim());
        if (StringUtils.hasLength((filter.getEmail())))
            criteria.and("email").is(filter.getEmail().trim());
        if (StringUtils.hasLength(filter.getPhone()))
            criteria.and(("phone")).is(filter.getPhone());

        query.addCriteria(criteria);
        return mongoTemplate.find(query, Employee.class);
    }

    public List<Employee> search(String keyword) {
        Query query = new Query();
        Criteria criteria = new Criteria().orOperator(Criteria.where("ansiName").regex(keyword, "i"),
                Criteria.where("phone").regex(keyword, "i"),
                Criteria.where("email").regex(keyword, "i"));
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Employee.class);
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
        Criteria criteria = new Criteria().andOperator(Criteria.where("salary").gte(10000000),
                Criteria.where("salary").lte(20000000));
        // criteria.and("salary").gte(10000000);
        //criteria.and("salary").lte(20000000);
        query.addCriteria(criteria);
        //Criteria.where("salary").gte(10000000).and("salary").lte(20000000);
        //query.addCriteria(Criteria.where("salary").gte(10000000).and("salary").lte(20000000));
        return mongoTemplate.find(query, Employee.class);
    }

    public int getTotalUser() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("delete").is(false);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Employee.class).size();
    }

    public int getActiveUser() {
        Query query = new Query();
        Criteria criteria = new Criteria().andOperator(Criteria.where("delete").is(false), Criteria.where("status").is(0));
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Employee.class).size();
    }

    public int getNonActiveUser() {
        Query query = new Query();
        Criteria criteria = new Criteria().andOperator(Criteria.where("delete").is(false), Criteria.where("status").is(1));
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Employee.class).size();
    }

}
