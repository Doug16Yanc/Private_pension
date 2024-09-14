package douglas.repository;

import douglas.domain.entity.Recipient;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class RecipientRepository implements PanacheRepositoryBase<Recipient, UUID> {
}
