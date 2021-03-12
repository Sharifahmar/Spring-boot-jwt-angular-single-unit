package com.spring.service;

import java.util.List;

import com.spring.model.ExtendValidityDTO;
import com.spring.model.User;
import com.spring.model.UserDTO;

public interface UserService {

	User save(User user);

	List<User> findAll();

	void delete(long id);

	User findOne(String username);

	User findById(Long id);

	User save(UserDTO user) throws Exception;

	String updateValidity(ExtendValidityDTO exDto) throws Exception;
}
