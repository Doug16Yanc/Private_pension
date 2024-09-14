package douglas.domain.entity;

import douglas.domain.enums.TypePlan;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_plans")
public class Plan extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    public TypePlan typePlan;

    public Double rate;

    @ManyToOne
    @JoinColumn(name = "customer_cpf")
    public Customer customer;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Investment> investments;
}
