package DB;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/calculations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalculationResource {

    @EJB
    private CalculationService service;

    @POST
    @Path("/calc")
    public Response createCalculation(Calculation calculation) {
        Calculation result = service.createCalculation(calculation);
        return Response.ok(Collections.singletonMap("Result", result.getNumber1())).build();
    }

    @GET
    public List<Calculation> getAllCalculations() {
        return service.getAllCalculations();
    }
}
