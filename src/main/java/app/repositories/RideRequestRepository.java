package app.repositories;

import java.util.List;

import app.models.RideRequest;

public interface RideRequestRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(RideRequest entity);
	
	RideRequest update(RideRequest entity);
	
	void destroy(RideRequest entity);
	
	RideRequest find(Long id);
	
	List<RideRequest> findAll();

}
