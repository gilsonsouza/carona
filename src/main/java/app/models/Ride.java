package app.models;

import javax.persistence.Embeddable;
import app.controllers.RideController;
import br.com.caelum.vraptor.restfulie.relation.RelationBuilder;

@javax.persistence.Entity
@Embeddable
public class Ride extends Entity {
	
	private String route;
	private String driver;
	private String street1;
	private String street2;
	private String street3;
	private String destiny;
	private String ridedays;
	private String passenger;
	private String passengerLimit;
	private String price;
	
	public String getRidedays() {
		return ridedays;
	}


	public void setRidedays(String ridedays) {
		this.ridedays = ridedays;
	}


	public String getStreet1() {
		return street1;
	}


	public void setStreet1(String street1) {
		this.street1 = street1;
	}


	public String getStreet2() {
		return street2;
	}


	public void setStreet2(String street2) {
		this.street2 = street2;
	}


	public String getStreet3() {
		return street3;
	}


	public void setStreet3(String street3) {
		this.street3 = street3;
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


	public String getDriver() {
		return driver;
	}


	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getPassenger() {
		return passenger;
	}

	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}

	public String getPassengerLimit() {
		return passengerLimit;
	}

	public void setPassengerLimit(String passengerLimit) {
		this.passengerLimit = passengerLimit;
	}

	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


    @Override
    public void configureRelations(RelationBuilder builder) {
        Class<RideController> controller = RideController.class;
        builder.relation("location").uses(controller).show(this);
        builder.relation("update").uses(controller).update(this);
        builder.relation("destroy").uses(controller).destroy(this);
    }

}
