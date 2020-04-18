package io.lucasvalenteds.toll.web.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lucasvalenteds.toll.board.PriceBoard;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;

@Path("/pricing")
@Produces(MediaType.TEXT_PLAIN)
@Component
public class PriceBoardResource {

    @Inject
    private PriceBoard priceBoard;

    @GET
    public String show() {
        return priceBoard.getBoard();
    }
}
