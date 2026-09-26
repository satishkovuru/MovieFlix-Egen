package io.egen.api.repository;

import java.util.List;

import io.egen.api.entity.User;

public interface UserRepository {

	public List<User> ListAllUsers();

	public User findByEmail(String email);

	public User create(User signupdetails);

	public User validateUser(User userlogindetails);

	public User updateUserInformation(User user);

	public User findUserByEmail(User user);
}
