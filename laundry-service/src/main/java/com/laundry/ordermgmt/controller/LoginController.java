package com.laundry.ordermgmt.controller;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.laundry.ordermgmt.repository.entity.Users;
import com.laundry.ordermgmt.service.LoginService;

@RestController
public class LoginController implements ILoginController {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private LoginService loginService;

	@Override
	public ResponseEntity<Users> login(String emailId, String password) {
		logger.info("Login User : {}", emailId);
		Users user = new Users();
		user.setEmailId(emailId);
		user.setPassword(password);
		// Login service
		return new ResponseEntity<Users>(loginService.login(user), HttpStatus.OK);

	}

}
