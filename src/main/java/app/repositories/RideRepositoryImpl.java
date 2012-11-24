package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.Ride;

@Component
public class RideRepositoryImpl extends Repository<Ride, Long>
		implements RideRepository {

	RideRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
