package app.repositories;

import java.util.List;

import app.models.Passengerride;

public interface PassengerrideRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Passengerride entity);
	
	Passengerride update(Passengerride entity);
	
	void destroy(Passengerride entity);
	
	Passengerride find(Long id);
	
	List<Passengerride> findAll();

}
