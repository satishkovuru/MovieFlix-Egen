package io.egen.api.repository;

import java.util.List;

import io.egen.api.entity.Movie;


public interface MovieRepository {

public void insertMovieDetail(Movie movie);
	
	public List<Movie> getAllMovies();
	
	public List<Movie> filterMovies(String filterType, String filterValue);
	
	public List<Movie> sortMovies(String filterType,String sortOrder);
	
	public Movie findByImdbId(String imdbId);
	
	public Movie findOne(String id);
	
	public Movie updateMovieDetail(Movie movie);
	
	public void deleteMovie(Movie movie);
}
