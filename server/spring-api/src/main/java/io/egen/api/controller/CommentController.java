package io.egen.api.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.api.constants.SessionVariables;
import io.egen.api.entity.Comments;
import io.egen.api.entity.Movie;
import io.egen.api.entity.User;
import io.egen.api.exception.LoginException;
import io.egen.api.service.CommentsService;
import io.egen.api.service.MovieServices;

@RestController
public class CommentController {

	@Autowired
	private CommentsService commentService;
	@Autowired
	private MovieServices movieService;


	@RequestMapping(method = RequestMethod.POST, value = "/insertComments/{movieId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Comments insertUserComment(HttpServletRequest request, @RequestBody Comments comments,@PathVariable("movieId") String movieId) {
		if (request.getSession().getAttribute(SessionVariables.SESSION_USER_ENTITY) == null)
			throw new LoginException("Please login first");
		User user = (User) request.getSession().getAttribute(SessionVariables.SESSION_USER_ENTITY);
		
		
		Movie mov= movieService.findOne(movieId);
		
		comments.setMovie(mov);
		comments.setComment(comments.getComment());
		comments.setUser(user);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		comments.setTimeStamp(timestamp);
		System.out.println("movie "+movieId+" user "+user.getId()+""+"timestamp "+timestamp);
		return commentService.insertUserComment(comments);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getComments/{movieId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Comments> getCommentsOnTitle(HttpServletRequest request, @PathVariable("movieId") String movieId) {
		if (request.getSession().getAttribute(SessionVariables.SESSION_USER_ENTITY) == null)
			throw new LoginException("Please login first");
		return commentService.getCommentsOnTitle(movieId);
	}

}
