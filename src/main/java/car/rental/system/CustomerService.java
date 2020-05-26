package car.rental.system;

import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;

@Singleton
public class CustomerService implements CustomerRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public CustomerService(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        String qlString = "SELECT c FROM Customer c";
        TypedQuery<Customer> query = entityManager.createQuery(qlString, Customer.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Customer save(@NotNull String name) {
        Customer newCustomer = new Customer(name);
        entityManager.persist(newCustomer);
        return newCustomer;
    }

    @Override
    @Transactional
    public int update(@NotNull Long id, @NotBlank String name) {
        return entityManager.createQuery("UPDATE Customer c SET name = :name where id = :id")
                .setParameter("name", name)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        findById(id).ifPresent(customer -> entityManager.remove(customer));
    }
}