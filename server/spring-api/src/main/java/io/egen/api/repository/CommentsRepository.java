package io.egen.api.repository;

import java.util.List;

import io.egen.api.entity.Comments;

public interface CommentsRepository {

	
	public Comments insertUserComment(Comments comments);

	public List<Comments> getCommentsOnTitle(String movieId);
}
