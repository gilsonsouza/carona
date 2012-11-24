package app.controllers;

import java.util.List;

import app.models.Questionary;
import app.repositories.QuestionaryRepository;
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
public class QuestionaryController {

  protected final Result result;
  protected final QuestionaryRepository repository;
  protected final Validator validator;

  public QuestionaryController(Result result, QuestionaryRepository repository, Validator validator) {
    this.result = result;
    this.repository = repository;
    this.validator = validator;
  }

  @Get("/questionaries")
  public void index() {
    serialize(repository.findAll());
  }

  @Post("/questionaries")
  @Consumes("application/json")
  public void create(Questionary questionary) {
    validate(questionary);
    repository.create(questionary);
    result.nothing();
  }

  @Put("/questionaries")
  @Consumes("application/json")
  public void update(Questionary questionary) {
    validate(questionary);
    repository.update(questionary);
    result.nothing();
  }

  @Get("/questionaries/{questionary.id}")
  public void show(Questionary questionary) {
    serialize(find(questionary));
  }

  @Delete("/questionaries/{questionary.id}")
  public void destroy(Questionary questionary) {
    repository.destroy(find(questionary));
    serialize(questionary);
  }

  private void serialize(Object object) {
    result.use(Results.json()).from(object).recursive().serialize();
  }

  private Questionary find(Questionary questionary) {
    return repository.find(questionary.getId());
  }

  private void validate(Questionary questionary) {
    validator.validate(questionary);
    if (validator.hasErrors()) {
        throw new ValidationException(validator.getErrors());
    }
  }
}

