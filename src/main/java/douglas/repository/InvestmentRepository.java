package douglas.repository;

import douglas.domain.entity.Investment;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InvestmentRepository implements PanacheRepositoryBase<Investment, Long> {
}
