package app.controllers;

import java.util.List;

import app.models.Passengerride;
import app.repositories.PassengerrideRepository;
import app.repositories.RideRepository;
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
public class PassengerrideController {

  protected final Result result;
  protected final PassengerrideRepository repository;
  protected final RideRepository riderepository;

  protected final Validator validator;

  public PassengerrideController(Result result, PassengerrideRepository repository, Validator validator, RideRepository riderepository) {
    this.result = result;
    this.repository = repository;
    this.riderepository = riderepository;
    this.validator = validator;
  }

  @Get("/passengerrides")
  public void index() {
    serialize(repository.findAll());
  }

  @Post("/passengerrides")
  @Consumes("application/json")
  public void create(Passengerride passengerride) {
    validate(passengerride);
    repository.create(passengerride);
    result.nothing();
  }

  @Put("/passengerrides")
  @Consumes("application/json")
  public void update(Passengerride passengerride) {
    validate(passengerride);
    repository.update(passengerride);
    result.nothing();
  }

  @Get("/passengerrides/{passengerride.id}")
  public void show(Passengerride passengerride) {
	passengerride = find(passengerride);
    serialize(passengerride);
	//serialize(find(passengerride));
  }

  @Delete("/passengerrides/{passengerride.id}")
  public void destroy(Passengerride passengerride) {
    repository.destroy(find(passengerride));
    serialize(passengerride);
  }
  

  private void serialize(Object object) {
    result.use(Results.json()).from(object).recursive().serialize();
  }

  private Passengerride find(Passengerride passengerride) {
    return repository.find(passengerride.getId());
  }

  private void validate(Passengerride passengerride) {
    validator.validate(passengerride);
    if (validator.hasErrors()) {
        throw new ValidationException(validator.getErrors());
    }
  }
}
