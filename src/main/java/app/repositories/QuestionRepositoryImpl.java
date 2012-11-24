package app.repositories;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.Question;

@Component
public class QuestionRepositoryImpl
    extends Repository<Question, Long>
    implements QuestionRepository {

	QuestionRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
