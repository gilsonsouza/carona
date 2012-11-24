package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.RideRequest;

@Component
public class RideRequestRepositoryImpl extends Repository<RideRequest, Long>
		implements RideRequestRepository {

	RideRequestRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
