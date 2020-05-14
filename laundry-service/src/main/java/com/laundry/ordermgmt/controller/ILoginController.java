package com.laundry.ordermgmt.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.laundry.ordermgmt.constant.SwaggerConstants;
import com.laundry.ordermgmt.constant.URLPath;
import com.laundry.ordermgmt.repository.entity.Users;

import io.swagger.annotations.Api;

@Api(tags = { SwaggerConstants.LOGIN_TAG })
@RequestMapping(value = URLPath.SLASH)
public interface ILoginController {

	@RequestMapping(value = URLPath.LOGIN
			+ URLPath.LOGIN, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	ResponseEntity<Users> login(@RequestParam(name = URLPath.PH_USER_NAME) String userName,
			@RequestParam(name = URLPath.PH_PASSWORD) String password);
}
