package io.egen.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.entity.Comments;
import io.egen.api.repository.CommentsRepository;


@Service
@Transactional
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsRepository repsitory;
	
	@Override
	public Comments insertUserComment(Comments comments) {
		return repsitory.insertUserComment(comments);
	}

	@Override
	public List<Comments> getCommentsOnTitle(String movieId) {
		return repsitory.getCommentsOnTitle(movieId);

	}

}
