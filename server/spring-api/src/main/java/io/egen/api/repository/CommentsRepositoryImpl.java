package io.egen.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import io.egen.api.entity.Comments;

@Repository
public class CommentsRepositoryImpl implements CommentsRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Comments insertUserComment(Comments comments) {
		em.persist(comments);
		return comments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comments> getCommentsOnTitle(String movieId) {
		Query query1=em.createNativeQuery("SELECT u.* FROM Comments u WHERE u.movie_id=?", "CommentEntityMapping");
		query1.setParameter(1, movieId);
		return query1.getResultList();
	}

}
