package com.dts.meclip.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dts.meclip.Service.UserService;
import com.dts.meclip.entity.User;

@RequestMapping("/user/api/auth/v1/user")
public class userApi {

	@Autowired
	UserService userService;
		
	@GetMapping(value = "/{mobile}")
//	@CrossOrigin(origins = "http://127.0.0.1:5501")
	public ResponseEntity<?> getUserByMobile(@PathVariable(name = "mobile") String mobile) {
		User user = userService.getUsserByMoblile(mobile);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
//	@CrossOrigin(origins = "http://127.0.0.1:5501")
	public ResponseEntity<?> getUserById(@PathVariable(name = "id") String id) {
		User user = userService.getUserByID(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
}
