package douglas.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import douglas.domain.enums.TypePlan;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_plans")
public class Plan extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Enumerated(EnumType.STRING)
    public TypePlan typePlan;

    public Double rate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer customer;

    @JsonManagedReference
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Investment> investments;
}
