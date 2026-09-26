package io.egen.api.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.egen.api.constants.FinalNamedQueries;
import lombok.Data;

@Entity
@Table(name = "Movie")
@NamedQueries(
		{	
			@NamedQuery(name="Movie.findByYear",query=FinalNamedQueries.MOVIE_FIND_BY_YEAR),
			@NamedQuery(name="Movie.findByYearAsc",query=FinalNamedQueries.MOVIE_FIND_BY_YEAR_ASC),
			@NamedQuery(name="Movie.findByTitle",query=FinalNamedQueries.MOVIE_FIND_BY_TITLE),
			//@NamedQuery(name="Movie.findByGenre",query=FinalNamedQueries.MOVIE_FIND_BY_GENRE),
			@NamedQuery(name="Movie.findByType",query=FinalNamedQueries.MOVIE_FIND_BY_TYPE),
			@NamedQuery(name="Movie.sortByVote",query=FinalNamedQueries.MOVIE_SORT_BY_VOTE),
			@NamedQuery(name="Movie.sortByVoteAsc",query=FinalNamedQueries.MOVIE_SORT_BY_VOTE_ASC),
			@NamedQuery(name="Movie.sortByRating",query=FinalNamedQueries.MOVIE_SORT_BY_RATING),
			@NamedQuery(name="Movie.sortByRatingAsc",query=FinalNamedQueries.MOVIE_SORT_BY_RATING_ASC),
			@NamedQuery(name="Movie.sortByYear",query=FinalNamedQueries.MOVIE_SORT_BY_YEAR),
			@NamedQuery(name="Movie.sortByYearAsc",query=FinalNamedQueries.MOVIE_SORT_BY_YEAR_ASC),
			@NamedQuery(name="Movie.findByImdbId",query=FinalNamedQueries.MOVIE_FIND_BY_IMDB_ID),
			@NamedQuery(name="Movie.filterByRatingMovieSeries",query=FinalNamedQueries.MOVIE_SORT_BY_RATING_MOVIE_SERIES)
		}
)
@SqlResultSetMapping(
        name = "Movie",
        entities = {
            @EntityResult(
                    entityClass = Movie.class,
                    fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "title", column = "title"),
                        @FieldResult(name = "year", column = "year"),
                        @FieldResult(name = "rated", column = "rated"),
                        @FieldResult(name = "released", column = "released"),
                        @FieldResult(name = "runtime", column = "runtime"),
                        @FieldResult(name = "genre", column = "genre"),
                        @FieldResult(name = "director", column = "director"),
                        @FieldResult(name = "writer", column = "writer"),
                        @FieldResult(name = "actors", column = "actors"),
                        @FieldResult(name = "plot", column = "plot"),
                        @FieldResult(name = "language", column = "language"),
                        @FieldResult(name = "country", column = "country"),
                        @FieldResult(name = "awards", column = "awards"),
                        @FieldResult(name = "poster", column = "poster"),
                        @FieldResult(name = "metaScore", column = "metaScore"),
                        @FieldResult(name = "imdbRating", column = "imdbRating"),
                        @FieldResult(name = "imdbVotes", column = "imdbVotes"),
                        @FieldResult(name = "imdbId", column = "imdbId"),
                        @FieldResult(name = "type", column = "type")})
           })

public @Data class Movie {
	
	@Id
	private String id;
	//@JsonProperty("Title")
	private String title;
	//@JsonProperty("Year")
    private String year;
	//@JsonProperty("Rated")
    private String rated;
	//@JsonProperty("Released")
    private String released;
	//@JsonProperty("Runtime")
    private String runtime;
	//@JsonProperty("Genre")
    private String genre;
	//@JsonProperty("Director")
    private String director;
	//@JsonProperty("Writer")
	@Column(columnDefinition="varchar(2000)")
    private String writer;
	//@JsonProperty("Actors")
    private String actors;
	//@JsonProperty("Plot")
	@Column(columnDefinition="varchar(800)")
    private String plot;
	//@JsonProperty("Language")
    private String language;
	//@JsonProperty("Country")
    private String country;
	//@JsonProperty("Awards")
    private String awards;
	//@JsonProperty("Poster")
    private String poster;
	//@JsonProperty("Metascore")
    private String metaScore;
    private String imdbRating;
    private String imdbVotes;
    @Column(unique=true)
    private String imdbId;
    //@JsonProperty("Type")
    private String type;
    
	public String getId() {
		return id;
	}
	
	public Movie() {
		this.id = UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getRated() {
		return rated;
	}
	public void setRated(String rated) {
		this.rated = rated;
	}
	public String getReleased() {
		return released;
	}
	public void setReleased(String released) {
		this.released = released;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getMetaScore() {
		return metaScore;
	}
	public void setMetascore(String metaScore) {
		this.metaScore = metaScore;
	}
	public String getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	public String getImdbVotes() {
		return imdbVotes;
	}
	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
