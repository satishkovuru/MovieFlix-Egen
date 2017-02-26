package io.egen.api.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Comments")
@SqlResultSetMapping(
        name = "CommentEntityMapping",
        entities = {
            @EntityResult(
                    entityClass = Comments.class,
                    fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "user", column = "user_id"),
                        @FieldResult(name = "movie", column = "movie_id"),
                        @FieldResult(name = "comment", column = "comment"),
                        @FieldResult(name = "timeStamp", column = "timeStamp")
                        })
           })
public @Data class Comments {

	
	@Id

	private String id;
	
	private User user;
	
	private Movie movie;
	
	private String comment;
	
	private Timestamp timeStamp;

	public Comments() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

}
