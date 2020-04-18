package io.lucasvalenteds.toll.web.toll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lucasvalenteds.toll.toll.Toll;
import io.lucasvalenteds.toll.toll.VehicleSensor;
import io.lucasvalenteds.toll.vehicle.Truck;
import io.lucasvalenteds.toll.vehicle.Vehicle;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;

@Path("/toll")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class TollResource {

    @Inject
    private ObjectMapper jsonMapper;

    @Inject
    private VehicleSensor sensor;

    @Inject
    private Toll toll;

    @GET
    @Path("/enter/{vehicle}")
    public Response enter(
        @PathParam("vehicle") String vehicleType,
        @QueryParam("money") Double money,
        @QueryParam("axles") Integer axles
    ) throws JsonProcessingException {
        Optional<Double> possibleMoney = Optional.ofNullable(money);
        if (!possibleMoney.isPresent()) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(jsonMapper.writeValueAsString(new TollResponseBody(
                    "Missing '?money=' query parameter. It should be a double."
                )))
                .build();
        }

        Optional<Vehicle> vehicleAllowedToPass = sensor.isVehicleAllowedToPass(vehicleType);
        if (!vehicleAllowedToPass.isPresent()) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(jsonMapper.writeValueAsString(new TollResponseBody(
                    "This type of vehicle shall not pass (type = " + vehicleType + ")"
                )))
                .build();
        }

        Vehicle vehicle = vehicleAllowedToPass.get();

        if (sensor.isVehicleOfType(vehicle, Truck.class)) {
            Truck truck = (Truck) vehicle;
            truck.setNumberOfAxles(axles);
            vehicle = truck;
        }

        try {
            return Response
                .status(Response.Status.OK)
                .entity(jsonMapper.writeValueAsString(toll.process(vehicle, possibleMoney.get())))
                .build();
        } catch (IllegalArgumentException exception) {
            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(jsonMapper.writeValueAsString(new TollResponseBody(exception.getMessage())))
                .build();
        }
    }
}
