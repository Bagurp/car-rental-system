package car.rental.system;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface CustomerRepository {
    Optional<Customer> findById(@NotNull Long id);
    List<Customer> findAll();
    Customer save(@NotNull String name);
    int update(@NotNull Long id, @NotBlank String name);
    void deleteById(@NotNull Long id);
}