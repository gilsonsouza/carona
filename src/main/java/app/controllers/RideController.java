package app.controllers;

import java.util.List;

import app.models.RideRequest;
import app.models.Ride;
import app.controllers.RideRequestController;
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
public class RideController {

  protected final Result result;
  protected final RideRepository repository;
  protected final Validator validator;

  public RideController(Result result, RideRepository repository, Validator validator) {
    this.result = result;
    this.repository = repository;
    this.validator = validator;
  }

  @Get("/rides")
  public void index() {
    serialize(repository.findAll());
  }

  @Post("/rides")
  @Consumes("application/json")
  public void create(Ride ride) {
    validate(ride);
    repository.create(ride);
    result.nothing();
  }

  @Put("/rides")
  @Consumes("application/json")
  public void update(Ride ride) {
    validate(ride);
    repository.update(ride);
    result.nothing();
  }

  @Get("/rides/{ride.id}")
  public void show(Ride ride) {
    serialize(find(ride));
  }

  @Delete("/rides/{ride.id}")
  public void destroy(Ride ride) {
    repository.destroy(find(ride));
    serialize(ride);
  }
  

  private void serialize(Object object) {
    result.use(Results.json()).from(object).recursive().serialize();
  }

  private Ride find(Ride ride) {
    return repository.find(ride.getId());
  }

  private void validate(Ride ride) {
    validator.validate(ride);
    if (validator.hasErrors()) {
        throw new ValidationException(validator.getErrors());
    }
  }
}
