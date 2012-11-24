package app.repositories;

import java.util.List;

import app.models.Questionary;

public interface QuestionaryRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Questionary entity);
	
	Questionary update(Questionary entity);
	
	void destroy(Questionary entity);
	
	Questionary find(Long id);
	
	List<Questionary> findAll();

}
