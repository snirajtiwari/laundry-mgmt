package com.laundry.ordermgmt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laundry.ordermgmt.repository.entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

	List<Users> findByUserName(String userName);

	List<Users> findByEmailId(String emailId);

}
