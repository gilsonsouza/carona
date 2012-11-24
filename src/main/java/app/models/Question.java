package app.models;

import app.controllers.QuestionController;
import br.com.caelum.vraptor.restfulie.relation.RelationBuilder;

@javax.persistence.Entity
public class Question extends Entity {
	
	private String doubt;
	private String answer;
	
	public void setAsk(String doubt) {
		this.doubt = doubt;
	}
	
	public String getDoubt() {
		return doubt;
	}
	
	public void setAnsw1(String answer) {
		this.answer = answer;
	}
	
	public String getAnsw1() {
		return answer;
	}
	

    @Override
    public void configureRelations(RelationBuilder builder) {
        Class<QuestionController> controller = QuestionController.class;
        builder.relation("location").uses(controller).show(this);
        builder.relation("update").uses(controller).update(this);
        builder.relation("destroy").uses(controller).destroy(this);
    }

}
