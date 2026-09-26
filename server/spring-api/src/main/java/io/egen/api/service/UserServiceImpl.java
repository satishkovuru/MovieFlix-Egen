package io.egen.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.entity.User;
import io.egen.api.exception.BadRequestException;
import io.egen.api.exception.LoginException;
import io.egen.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<User> ListAllUsers() {
		return repository.ListAllUsers();
	}

	@Transactional
	public User create(User signupdetails) {
		User existing = repository.findByEmail(signupdetails.getEmail());
		if (existing != null) {
			throw new BadRequestException("User with this email already exists");
		}
		return repository.create(signupdetails);
	}

	@Override
	public User validateUser(User userlogindetails) {
		User u = repository.validateUser(userlogindetails);
		return u;
	}

	@Override
	public User updateUserInformation(User loggeduser, User updateuserdetails) {
		User existing = repository.findUserByEmail(loggeduser);
		if (existing == null) {
			throw new LoginException("User with email:" + loggeduser.getEmail() + " does not exist");
		}
		return repository.updateUserInformation(updateuserdetails);
	}

}