package com.halflife.service;

import com.halflife.bean.User;
import com.halflife.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	@Autowired
	private UserRepository userRepository;

	public User login(User user) {
		return userRepository.findByName(user.getName());
	}

}
