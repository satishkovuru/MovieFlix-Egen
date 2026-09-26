package io.egen.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.entity.Movie;
import io.egen.api.exception.MoviesException;
import io.egen.api.repository.MovieRepository;

@Service
@Transactional
public class MovieServicesImpl implements MovieServices {

	@Autowired
	MovieRepository movieRepository;
	
	@Override
	public void insertMovieDetail(Movie movie) {
		movieRepository.insertMovieDetail(movie);
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.getAllMovies();
	}

	@Override
	public List<Movie> filterMovies(String filterType, String filterValue) {
		return movieRepository.filterMovies(filterType, filterValue);
	}

	@Override
	public List<Movie> sortMovies(String filterType, String sortOrder) {
		return movieRepository.sortMovies(filterType, sortOrder);
	}

	@Override
	public Movie findByImdbId(String imdbId) {
		return movieRepository.findByImdbId(imdbId);
	}

	@Override
	public Movie findOne(String id) {
		return movieRepository.findOne(id);
	}

	@Override
	public Movie updateMovieDetail(Movie movie) {
		Movie existing = movieRepository.findOne(movie.getId());
		if(existing==null)
			throw new MoviesException("Movie with ID:"+movie.getId()+"("+movie.getTitle()+") was not found in database");
		return movieRepository.updateMovieDetail(movie);
	}

	@Override
	public void deleteMovie(Movie movie) {
		movieRepository.deleteMovie(movie);
	}

}
