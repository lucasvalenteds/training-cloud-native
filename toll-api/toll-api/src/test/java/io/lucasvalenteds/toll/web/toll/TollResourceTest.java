package io.lucasvalenteds.toll.web.toll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lucasvalenteds.toll.payment.Checkout;
import io.lucasvalenteds.toll.web.config.DomainConfiguration;
import io.lucasvalenteds.toll.web.config.WebConfiguration;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig({DomainConfiguration.class, WebConfiguration.class})
public class TollResourceTest {

    @Autowired
    private ObjectMapper jsonObjectMapper;

    @Autowired
    private TollResource tollResource;

    @Test
    public void testItFailsIfMoneyIsNotInformedByTheCustomer() throws JsonProcessingException {
        Response response = tollResource.enter("bike", null, null);

        assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo().toEnum());
        assertEquals(
            jsonObjectMapper.writeValueAsString(new TollResponseBody(
                "Missing '?money=' query parameter. It should be a double."
            )),
            response.getEntity()
        );
    }

    @Test
    public void testItFailsIfTypeOfVehicleIsNotAllowedToPass() throws JsonProcessingException {
        String vehicleType = "foo";
        Response response = tollResource.enter(vehicleType, 1.0, null);

        assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo().toEnum());
        assertEquals(
            jsonObjectMapper.writeValueAsString(new TollResponseBody(
                "This type of vehicle shall not pass (type = " + vehicleType + ")"
            )),
            response.getEntity()
        );
    }

    @Test
    public void testItFailsIfMoneyInformedIsLessThanRequired() throws JsonProcessingException {
        Response response = tollResource.enter("bike", 0.1, null);

        assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo().toEnum());
        assertEquals(
            jsonObjectMapper.writeValueAsString(new TollResponseBody(
                "Money received is not enough. It was received " + 0.1 + " but should be at least " + 0.49
            )),
            response.getEntity()
        );
    }

    @Test
    public void testItAllowsToSpecifyTheNumberOfAxlesIfVehicleIsTruck() throws JsonProcessingException {
        Response response = tollResource.enter("truck", 6.0, 1);

        assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
        assertEquals(
            jsonObjectMapper.writeValueAsString(new Checkout(6.0, 1.55)),
            response.getEntity()
        );
    }

    @Test
    public void testBikeHappyPath() throws JsonProcessingException {
        Response response = tollResource.enter("bike", 1.0, null);

        assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
        assertEquals(
            jsonObjectMapper.writeValueAsString(new Checkout(1.0, 0.51)),
            response.getEntity()
        );
    }

}