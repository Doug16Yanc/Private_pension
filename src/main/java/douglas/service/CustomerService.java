package douglas.service;

import douglas.domain.entity.Customer;
import douglas.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer) {
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
                .orElseThrow();
    }

    public void deleteById(UUID id) {
        Customer customer = findById(id);
        customerRepository.delete(customer);
    }
}
