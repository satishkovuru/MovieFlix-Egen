package io.egen.api.service;

import java.util.List;

import io.egen.api.entity.Movie;

public interface MovieServices {
	public void insertMovieDetail(Movie movie);
	
	public List<Movie> getAllMovies();
	
	public List<Movie> filterMovies(String filterType, String filterValue);
	
	public List<Movie> sortMovies(String filterType,String sortOrder);
	
	public Movie findByImdbId(String imdbId);
	
	public Movie findOne(String id);
	
	public Movie updateMovieDetail(Movie movie);
	
	public void deleteMovie(Movie movie);
}
