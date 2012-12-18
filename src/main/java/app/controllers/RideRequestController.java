package app.controllers;

import java.util.List;

import app.models.Ride;
import app.models.RideRequest;
import app.repositories.RideRequestRepository;
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
public class RideRequestController {

  protected final Result result;
  protected final RideRequestRepository repository;
  protected final Validator validator;

  public RideRequestController(Result result, RideRequestRepository repository, Validator validator) {
    this.result = result;
    this.repository = repository;
    this.validator = validator;
  }

  @Get("/riderequests")
  public void index() {
    serialize(repository.findAll());
  }

  @Post("/riderequests")
  @Consumes("application/json")
  public void create(RideRequest riderequest) {
    validate(riderequest);
    repository.create(riderequest);
    result.nothing();
  }

  @Put("/riderequests")
  @Consumes("application/json")
  public void update(RideRequest riderequest) {
    validate(riderequest);
    repository.update(riderequest);
    result.nothing();
  }

  @Get("/riderequests/{riderequest.id}")
  public void show(RideRequest riderequest) {
    serialize(find(riderequest));
  }

  @Delete("/riderequests/{riderequest.id}")
  public void destroy(RideRequest riderequest) {
    repository.destroy(find(riderequest));
    serialize(riderequest);
  }
  
  @Post("/rides/requestride")
  @Consumes("application/json")
  public void requestRide(Ride form) {
    RideRequest request = new RideRequest();
    request.setDriver(form.getDriver());
    request.setRoute(form.getRoute());
    validate(request);
    repository.create(request);
    result.nothing();
  }
  
  private void serialize(Object object) {
    result.use(Results.json()).from(object).recursive().serialize();
  }

  private RideRequest find(RideRequest riderequest) {
    return repository.find(riderequest.getId());
  }

  private void validate(RideRequest riderequest) {
    validator.validate(riderequest);
    if (validator.hasErrors()) {
        throw new ValidationException(validator.getErrors());
    }
  }
}
