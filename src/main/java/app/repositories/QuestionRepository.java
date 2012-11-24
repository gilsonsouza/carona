package app.repositories;

import java.util.List;

import app.models.Question;

public interface QuestionRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Question entity);
	
	Question update(Question entity);
	
	void destroy(Question entity);
	
	Question find(Long id);
	
	List<Question> findAll();

}
