package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.Questionary;

@Component
public class QuestionaryRepositoryImpl
    extends Repository<Questionary, Long>
    implements QuestionaryRepository {

	QuestionaryRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
