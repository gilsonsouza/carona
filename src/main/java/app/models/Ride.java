package app.models;

import app.controllers.RideController;
import br.com.caelum.vraptor.restfulie.relation.RelationBuilder;

@javax.persistence.Entity
public class Ride extends Entity {
	
	private String route;
	private String driver;
	private String passenger;
	private String passengerLimit;
	private String price;

	
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
