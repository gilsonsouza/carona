package app.models;

import app.controllers.RideRequestController;
import br.com.caelum.vraptor.restfulie.relation.RelationBuilder;

@javax.persistence.Entity
public class RideRequest extends Entity {
	
	private String driver;
	private String passenger;
	private String route;
	
	


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




	public String getRoute() {
		return route;
	}




	public void setRoute(String route) {
		this.route = route;
	}




	@Override
    public void configureRelations(RelationBuilder builder) {
        Class<RideRequestController> controller = RideRequestController.class;
        builder.relation("location").uses(controller).show(this);
        builder.relation("update").uses(controller).update(this);
        builder.relation("destroy").uses(controller).destroy(this);
    }

}
