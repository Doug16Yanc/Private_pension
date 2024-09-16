package douglas.service;

import douglas.domain.entity.Customer;
import douglas.domain.entity.Investment;
import douglas.exception.investments.InvestmentNotFoundException;
import douglas.repository.InvestmentRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class InvestmentService {

    private final InvestmentRepository investmentRepository;

    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public Investment create(Investment investment) {
        investmentRepository.persist(investment);
        return investment;
    }

    public List<Investment> findAll(Integer page, Integer size) {
        return investmentRepository.findAll()
                .page(page, size)
                .list();
    }

    public Investment findById(Long id) {
        return investmentRepository.findByIdOptional(id)
                .orElseThrow(InvestmentNotFoundException::new);
    }

    public Customer findByCpf(String cpf) {
        PanacheQuery<Customer> query = Customer.find("cpf", cpf);
        return query.firstResult();
    }

    public void deleteById(Long id) {
        Investment investment = findById(id);
        investmentRepository.delete(investment);
    }
}

