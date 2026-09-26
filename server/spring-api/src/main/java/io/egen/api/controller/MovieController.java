package io.egen.api.controller;

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
import io.egen.api.entity.Movie;
import io.egen.api.entity.MovieList;
import io.egen.api.entity.User;
import io.egen.api.exception.LoginException;
import io.egen.api.exception.MoviesException;
import io.egen.api.service.MovieServices;

@RestController
public class MovieController {
	@Autowired
	MovieServices movieService;
	
	@RequestMapping(method=RequestMethod.POST,value="/insertMovieDetail",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void insertMovieDetail(HttpServletRequest request, @RequestBody MovieList moviesList)
	{
		
		request.getSession().getAttribute("session_user_entity");
		if(!((User)request.getSession().getAttribute(SessionVariables.SESSION_USER_ENTITY)).getRole().equalsIgnoreCase("ADMIN"))
			throw new LoginException("Admin can only add the movies into the Database");
		List<Movie> movie = null;
		if (moviesList != null) {
			movie = moviesList.getMovieList();
			if (!movie.isEmpty()) {
				for (Movie movies : movie)
					movieService.insertMovieDetail(movies);
			} else
				throw new MoviesException("Passed movie list is empty");
		} else
			throw new MoviesException("Request body is empty");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/deleteMovies", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void deleteMovies(HttpServletRequest request, @RequestBody MovieList moviesList) {
		if (!((User) request.getSession().getAttribute(SessionVariables.SESSION_USER_ENTITY)).getRole().equalsIgnoreCase("ADMIN"))
			throw new LoginException("Only admin can delete movie details");
		List<Movie> movie = null;
		if (moviesList != null) {
			movie = moviesList.getMovieList();
			if (!movie.isEmpty()) {
				for (Movie movies : movie)
					movieService.deleteMovie(movies);
			}
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateMovieDetail", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie updateMovieDetail(HttpServletRequest request, @RequestBody Movie movies) {
		if (request.getSession().getAttribute(SessionVariables.SESSION_USER_ENTITY) == null)
			throw new LoginException("Please login first");
		return movieService.updateMovieDetail(movies);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAllMovies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> getAllMovies(HttpServletRequest request) {
		if (request.getSession().getAttribute(SessionVariables.SESSION_USER_ENTITY) == null)
			throw new LoginException("Please login first");
		return movieService.getAllMovies();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getImdbIdMovies/{imdbId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie getMovieByImbdId(HttpServletRequest request, @PathVariable("imdbId") String imdbMovieId) {
		if (request.getSession().getAttribute(SessionVariables.SESSION_USER_ENTITY) == null)
			throw new LoginException("Please login first");
		return movieService.findByImdbId(imdbMovieId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAllMovies/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie getMovieById(HttpServletRequest request, @PathVariable("id") String movieId) {
		if (request.getSession().getAttribute(SessionVariables.SESSION_USER_ENTITY) == null)
			throw new LoginException("Please login first");
		return movieService.findOne(movieId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/filterMovies/{filterType}/{filterValue}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> filterMovies(@PathVariable("filterType") String filterType,
			@PathVariable("filterValue") String filterValue) {
		return movieService.filterMovies(filterType, filterValue);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/sortMovies/{filterType}/{sortOrder}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> sortMovies(@PathVariable(value = "filterType") String filterType,
			@PathVariable(value = "sortOrder") String sortOrder) {
		return movieService.sortMovies(filterType, sortOrder);
	}

}
