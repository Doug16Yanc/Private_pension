package douglas.service;

import douglas.domain.entity.Recipient;
import douglas.exception.customers.CustomerAlreadyExistentException;
import douglas.exception.recipients.RecipientAlreadyExistentException;
import douglas.repository.RecipientRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RecipientService {

    private final RecipientRepository recipientRepository;

    public RecipientService(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    public Recipient create(Recipient recipient) {

        var existing = findByCpf(recipient.cpf);
        if (existing != null) {
            return null;        }
        recipientRepository.persist(recipient);
        return recipient;
    }

    public List<Recipient> findAll(Integer page, Integer size) {
        return recipientRepository.findAll()
                .page(page, size)
                .list();
    }

    public Recipient findById(Long id) {
        return recipientRepository.findByIdOptional(id)
               .orElseThrow(RecipientAlreadyExistentException::new);
    }

    public Recipient findByCpf(String cpf) {
        PanacheQuery<Recipient> query = Recipient.find("cpf", cpf);
        return query.firstResult();
    }

    public void deleteById(Long id) {
        Recipient recipient = findById(id);
        recipientRepository.delete(recipient);
    }

}
