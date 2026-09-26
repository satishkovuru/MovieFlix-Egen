package io.egen.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.api.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<User> ListAllUsers() {
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public User create(User signupdetails) {
		em.persist(signupdetails);
		return signupdetails;
	}

	@Override
	public User findByEmail(String email) {
		TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("pEmail", email);

		List<User> employees = query.getResultList();
		if (employees != null && employees.size() == 1) {
			return employees.get(0);
		} else {
			return null;
		}
	}

	@Override
	public User validateUser(User userlogindetails) {
		Query query = em.createNativeQuery("SELECT u.* FROM User u WHERE BINARY u.username=? AND u.password=?",
				"UserEntityMapping");
		query.setParameter(1, userlogindetails.getUsername());
		query.setParameter(2, userlogindetails.getPassword());
		User a = (User) query.getSingleResult();
		return a;

	}

	// New Features

	@Override
	public User updateUserInformation(User user) {
		return em.merge(user);

	}

	@Override
	public User findUserByEmail(User user) {
		TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("pEmail", user.getEmail());
		return query.getSingleResult();
	}

}