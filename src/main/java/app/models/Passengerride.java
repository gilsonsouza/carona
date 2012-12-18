package app.models;

import app.controllers.PassengerrideController;

import app.controllers.RideController;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Transient;

import app.models.Ride;
import br.com.caelum.vraptor.restfulie.relation.RelationBuilder;

@javax.persistence.Entity
public class Passengerride extends Entity {

	private String route;
	private String passenger;
	private String origin;
	private String destiny;
	private String ridedays;
	
	@Transient
	private List<Ride> rides;

	public List<Ride> getRides() {
		return rides;
	}

	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getPassenger() {
		return passenger;
	}

	public void setPassenger(String passanger) {
		this.passenger = passanger;
	}

	public String getRidedays() {
		return ridedays;
	}

	public void setRidedays(String ridedays) {
		this.ridedays = ridedays;
	}

	public String getDestiny() {
		return destiny;
	}

	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	@Override
	public void configureRelations(RelationBuilder builder) {
		Class<PassengerrideController> controller = PassengerrideController.class;
		builder.relation("location").uses(controller).show(this);
		builder.relation("update").uses(controller).update(this);
		builder.relation("destroy").uses(controller).destroy(this);
		try {
			builder.relation("rides").uses(RideController.class).create(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
