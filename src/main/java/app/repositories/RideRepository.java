package app.repositories;

import java.util.List;

import app.models.Ride;

public interface RideRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Ride entity);
	
	Ride update(Ride entity);
	
	void destroy(Ride entity);
	
	Ride find(Long id);
	
	List<Ride> findAll();

}
