package douglas.service;

import douglas.domain.entity.Customer;
import douglas.exception.CustomerAlreadyExistentExpection;
import douglas.exception.CustomerNotFoundException;
import douglas.repository.CustomerRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer) {

        var existing = findByCpf(customer.cpf);
        if (existing != null) {
            throw new CustomerAlreadyExistentExpection();
        }
        customerRepository.persist(customer);
        return customer;
    }

    public List<Customer> findAll(Integer page, Integer size) {
        return customerRepository.findAll()
                .page(page, size)
                .list();
    }

    public Customer findById(UUID id) {
        return (Customer) customerRepository.findByIdOptional(id)
                .orElseThrow(CustomerNotFoundException::new);
    }

    public Customer findByCpf(String cpf) {
        PanacheQuery<Customer> query = Customer.find("cpf", cpf);
        return query.firstResult();
    }

    public void deleteById(UUID id) {
        Customer customer = findById(id);
        customerRepository.delete(customer);
    }
}
