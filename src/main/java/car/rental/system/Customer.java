package car.rental.system;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Customer {
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Customer() {   
    }

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer(@NotNull String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}