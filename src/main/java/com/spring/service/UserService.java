package com.spring.service;

import com.spring.model.ExtendValidityDTO;
import com.spring.model.User;
import com.spring.model.UserDTO;

import java.util.List;

public interface UserService {

	User save(User user);

	List<User> findAll();

	void delete(long id);

	User findOne(String username);

	User findById(Long id);

	User save(UserDTO user) throws Exception;

	String updateValidity(ExtendValidityDTO exDto) throws Exception;
}
