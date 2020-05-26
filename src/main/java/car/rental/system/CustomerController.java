package car.rental.system;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

@Controller("/crental")
public class CustomerController {
    
    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    @Get("/{id}")
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Get("/")
    public List<Customer> list() {
        return customerRepository.findAll();
    }
    
    @Post("/")
    public HttpResponse<Customer> save(@Body @Valid Customer customer) {
        Customer newCustomer = customerRepository.save(customer.getName());
        
        return HttpResponse
                .created(newCustomer)
                .headers(headers -> headers.location(location(newCustomer.getId())));
    }

    @Put("/")
    public HttpResponse update(@Body @Valid Customer customer) {
        int numberOfEntitiesUpdated = customerRepository.update(customer.getId(), customer.getName());

        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, location(customer.getId()).getPath());
    }

    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        customerRepository.deleteById(id);
        return HttpResponse.noContent();
    }

    protected URI location(Long id) {
        return URI.create("/customer/" + id);
    }

    protected URI location(Customer customer) {
        return location(customer.getId());
    }
}