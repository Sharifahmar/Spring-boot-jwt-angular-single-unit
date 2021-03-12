package com.spring.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.spring.dao.UserDao;
import com.spring.model.ExtendValidityDTO;
import com.spring.model.User;
import com.spring.model.UserDTO;
import com.spring.service.UserService;
import com.spring.utils.CommonUtils;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userDao.findByUsername(userId);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public User save(UserDTO user) throws Exception {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setCreatedDate(CommonUtils.stringTodate(user.getCreatedDate()));
		newUser.setToDate(CommonUtils.stringTodate(user.getToDate()));
		return userDao.save(newUser);

	}

	@Override
	public String updateValidity(ExtendValidityDTO exDto) throws Exception {
		String message = null;
		Optional<User> user = Optional.ofNullable(userDao.findByUsername(exDto.getUsername()));
		if (user.isPresent()) {
			user.get().setToDate(CommonUtils.stringTodate(exDto.getToDate()));
			User userUpdate = userDao.save(user.get());
			if (!ObjectUtils.isEmpty(userUpdate)) {
				message = "Validity Extend Successfully..!";
			} else {
				message = "Something went wrong.!";
			}
		} else {
			message = "Username not found..!";
		}
		return message;
	}
}
