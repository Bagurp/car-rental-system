package car.rental.system;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/heartbeat") 
public class HeartbeatController {

    @Get("/") 
    @Produces(MediaType.TEXT_PLAIN) 
    public String index() {
        return "Ah, ha, ha, ha, stayin' alive"; 
    }
}