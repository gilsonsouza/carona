package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.Passengerride;

@Component
public class PassengerrideRepositoryImpl extends Repository<Passengerride, Long>
		implements PassengerrideRepository {

	PassengerrideRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
