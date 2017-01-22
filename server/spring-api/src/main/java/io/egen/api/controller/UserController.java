package io.egen.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.api.constants.SessionVariables;
import io.egen.api.entity.User;
import io.egen.api.exception.LoginException;
import io.egen.api.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET, value = "/getusers")
	public List<User> ListAllUsers() {
		return service.ListAllUsers();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/SignUp")
	public User create(@RequestBody User signupdetails) {
		return service.create(signupdetails);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/SignIn",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String validate(HttpServletRequest request, @RequestBody User userlogindetails) {

		User user = service.validateUser(userlogindetails);
		HttpSession session = request.getSession(true);
		session.setAttribute(SessionVariables.SESSION_USER_ENTITY, user);
		session.setMaxInactiveInterval(SessionVariables.SESSION_MAX_INACTIVE_TIME);
		return user.getId();

	}

	@RequestMapping(method = RequestMethod.GET, value = "/signOut")
	public void signOut(HttpServletRequest request) {
		request.getSession().invalidate();
	}

	// New Features

	@RequestMapping(method = RequestMethod.PUT, value = "/updateUserDetail", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User updateUserDetail(HttpServletRequest request, @RequestBody User updateuserdetails) {
		HttpSession session = request.getSession(true);
		User loggedUser = (User) session.getAttribute(SessionVariables.SESSION_USER_ENTITY);
		if (null == loggedUser || !(loggedUser.getId().equals(updateuserdetails.getId())))
			throw new LoginException("Please login first");
		User userEntity = service.updateUserInformation(loggedUser, updateuserdetails);
		session.setAttribute(SessionVariables.SESSION_USER_ENTITY, userEntity);
		return userEntity;
	}
	

}