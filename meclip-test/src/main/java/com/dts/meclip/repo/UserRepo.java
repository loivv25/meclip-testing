package com.dts.meclip.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dts.meclip.entity.User;
@Repository
public interface UserRepo extends MongoRepository<User,String> {
	    User findByMobile(String mobile);
	    User findBySocialId(String socialId);
	    boolean existsByMobile(String mobile);

	

}
