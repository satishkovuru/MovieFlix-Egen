package io.egen.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.api.entity.Movie;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void insertMovieDetail(Movie movie) {
		em.persist(movie);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getAllMovies() {
		return em.createNativeQuery("SELECT * from Movie", Movie.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> filterMovies(String filterType, String filterValue) {
		TypedQuery<Movie> query=null;
		if(filterType.equalsIgnoreCase("YEAR"))
		{
			query=em.createNamedQuery("Movie.findByYear", Movie.class);
			query.setParameter("pYear", filterValue);
		}
		else if(filterType.equalsIgnoreCase("TITLE"))
		{
			Query query1=em.createNativeQuery("SELECT u.* from Movie u WHERE u.title LIKE '%"+filterValue+"%'", "MovieMapping");
			return query1.getResultList();
		}
		else if(filterType.equalsIgnoreCase("GENRE"))
		{	
			Query query1=em.createNativeQuery("SELECT u.* from Movie u WHERE u.genre LIKE '%"+filterValue+"%'", "MovieMapping");
			return query1.getResultList();
		}
		else if(filterType.equalsIgnoreCase("TYPE"))
		{
			query=em.createNamedQuery("Movie.findByType", Movie.class);
			query.setParameter("pType", filterValue);
		}
		else if(filterType.equalsIgnoreCase("MOVIE_SERIES_RATING"))
		{
			query=em.createNamedQuery("Movie.filterByRatingMovieSeries", Movie.class);
			query.setParameter("pType", filterValue);
		}
		return query.getResultList();
	}

	@Override
	public List<Movie> sortMovies(String filterType, String sortOrder) 
	{
		TypedQuery<Movie> query=null;
		if(filterType.equalsIgnoreCase("VOTE"))
		{
			if(sortOrder.equalsIgnoreCase("ASC"))
				query=em.createNamedQuery("Movie.sortByVoteAsc", Movie.class);
			else if(sortOrder.equalsIgnoreCase("DESC"))
				query=em.createNamedQuery("Movie.sortByVote", Movie.class);
		}
		else if(filterType.equalsIgnoreCase("RATING"))
		{
			if(sortOrder.equalsIgnoreCase("ASC"))
				query=em.createNamedQuery("Movie.sortByRatingAsc", Movie.class);
			else if(sortOrder.equalsIgnoreCase("DESC"))
				query=em.createNamedQuery("Movie.sortByRating", Movie.class);
		}
		else if(filterType.equalsIgnoreCase("YEAR"))
		{
			if(sortOrder.equalsIgnoreCase("ASC"))
				query=em.createNamedQuery("Movie.sortByYearAsc", Movie.class);
			else if(sortOrder.equalsIgnoreCase("DESC"))
				query=em.createNamedQuery("Movie.sortByYear", Movie.class);
		}
		return query.getResultList();
	}

	@Override
	public Movie findByImdbId(String imdbId) {
		TypedQuery<Movie> query=em.createNamedQuery("Movie.findByImdbId", Movie.class);
		query.setParameter("pImdbId", imdbId);
		return query.getSingleResult();
	}

	@Override
	public Movie findOne(String id) {
		return em.find(Movie.class, id);
	}

	@Override
	public Movie updateMovieDetail(Movie movie) {
		return em.merge(movie);
	}

	@Override
	public void deleteMovie(Movie movie) {
		em.remove(movie);
	}

}
