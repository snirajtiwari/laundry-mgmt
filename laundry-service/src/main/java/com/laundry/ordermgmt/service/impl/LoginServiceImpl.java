package com.laundry.ordermgmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.laundry.ordermgmt.constant.ApplicationMessages;
import com.laundry.ordermgmt.repository.UserRepository;
import com.laundry.ordermgmt.repository.entity.Users;
import com.laundry.ordermgmt.service.LoginService;

@Component
@Transactional
public class LoginServiceImpl implements LoginService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Users login(Users user) {
		List<Users> usersinDB = userRepository.findByUserName(user.getUserName());
		if (usersinDB.isEmpty()) {
			throw new ValidationException(
					String.format(ApplicationMessages.FIELD_DOES_NOT_EXIST_IN_DB, user.getUserName()));
		}

		Users userinDB = usersinDB.get(0);

		Boolean match = new BCryptPasswordEncoder().matches(user.getPassword(), userinDB.getPassword());
		if (match) {
			userinDB.setLastLoginDate(new Date());
			return userRepository.save(userinDB);
		} else {
			throw new ValidationException(ApplicationMessages.INCORRECT_PASSWORD);
		}
	}

}
