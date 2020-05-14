package com.laundry.ordermgmt.service;

import java.io.Serializable;

import com.laundry.ordermgmt.repository.entity.Users;

public interface LoginService extends Serializable {

	Users login(Users user);

}
