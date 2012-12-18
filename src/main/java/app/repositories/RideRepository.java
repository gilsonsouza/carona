package app.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import app.models.Ride;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class RideRepository extends Repository<Ride, Long> {

	RideRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	public List<Ride> listAllOringinAndDestiniy(String origin, String destiny) {
		String jpql = "from Ride where destiny = '" + destiny + "' and street1 = '" + origin + "'";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
}
