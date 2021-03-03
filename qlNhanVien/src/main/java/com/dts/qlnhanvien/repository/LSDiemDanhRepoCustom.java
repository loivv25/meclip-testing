package com.dts.qlnhanvien.repository;

import com.dts.qlnhanvien.document.Employee;
import com.dts.qlnhanvien.document.LSDiemDanh;
import com.dts.qlnhanvien.request.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Repository
public class LSDiemDanhRepoCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    public boolean checkCheckin(String id) {

        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("checkinTime").equals(new Date());
        criteria.and("employeeId").is(id);
        query.addCriteria(criteria);
        return mongoTemplate.exists(query, LSDiemDanh.class);
    }

    public List<LSDiemDanh> getListCheckInToday(int start, int limit) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("checkinTime").equals(new Date());
        query.addCriteria(criteria);
        query.skip(start);
        query.limit(limit);
        return mongoTemplate.find(query, LSDiemDanh.class);
    }

    public List<LSDiemDanh> searchCheckIn(Date startDate, Date endDate, int start, int limit) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("checkinTime").gte(startDate);
        criteria.and("checkinTime").lte(endDate);
        query.skip(start);
        query.limit(limit);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, LSDiemDanh.class);
    }


}
