package app.controllers;

import java.util.List;

import app.models.Question;
import app.repositories.QuestionRepository;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.view.Results;

@Resource
public class QuestionController {

  protected final Result result;
  protected final QuestionRepository repository;
  protected final Validator validator;

  public QuestionController(Result result, QuestionRepository repository, Validator validator) {
    this.result = result;
    this.repository = repository;
    this.validator = validator;
  }

  @Get("/questions")
  public void index() {
    serialize(repository.findAll());
  }

  @Post("/questions")
  @Consumes("application/json")
  public void create(Question question) {
    validate(question);
    repository.create(question);
    result.nothing();
  }

  @Put("/questions")
  @Consumes("application/json")
  public void update(Question question) {
    validate(question);
    repository.update(question);
    result.nothing();
  }

  @Get("/questions/{question.id}")
  public void show(Question question) {
    serialize(find(question));
  }

  @Delete("/questions/{question.id}")
  public void destroy(Question question) {
    repository.destroy(find(question));
    serialize(question);
  }

  private void serialize(Object object) {
    result.use(Results.json()).from(object).recursive().serialize();
  }

  private Question find(Question question) {
    return repository.find(question.getId());
  }

  private void validate(Question question) {
    validator.validate(question);
    if (validator.hasErrors()) {
        throw new ValidationException(validator.getErrors());
    }
  }
}
