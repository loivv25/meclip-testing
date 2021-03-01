package com.dts.meclip.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dts.meclip.entity.User;
import com.dts.meclip.repo.UserRepo;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public User getUserByID(String id) {
		User result = userRepo.findBySocialId(id);
		return result;
	}

	@Override
	public User getUsserByMoblile(String mobile) {
		return userRepo.findByMobile(mobile);
	}

}
