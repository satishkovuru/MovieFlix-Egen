package io.egen.api.service;

import java.util.List;

import io.egen.api.entity.User;

public interface UserService {

	public List<User> ListAllUsers();

	public User create(User user);

	public User validateUser(User userlogindetails);

	public User updateUserInformation(User loggeduser, User updateuserdetails);

}
