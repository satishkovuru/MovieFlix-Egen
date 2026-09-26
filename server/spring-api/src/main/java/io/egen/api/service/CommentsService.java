package io.egen.api.service;

import java.util.List;

import io.egen.api.entity.Comments;

public interface CommentsService {

public Comments insertUserComment(Comments comments);
	
	public List<Comments> getCommentsOnTitle(String movieId);
}
