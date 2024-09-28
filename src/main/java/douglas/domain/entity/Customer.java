package douglas.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import douglas.domain.enums.TypePlan;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "tb_customers")
public class Customer extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    public String cpf;

    public String email;

    public String phone;

    @Embedded
    public Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Plan> plans = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Recipient> recipients = new ArrayList<>();

    public void addRecipient(Recipient recipient) {
        if (recipients.size() >= 1) {
            throw new IllegalArgumentException("O número máximo de beneficiários é um.");
        }
        this.recipients.add(recipient);
    }

    public void addPlan(Plan plan) {
        if (plan.typePlan == TypePlan.VGBL && countPlanVGBL() >= 1) {
            throw new IllegalArgumentException("Você só pode possuir um plano do tipo VGBL.");
        }
        if (plan.typePlan == TypePlan.PGBL && countPlanPGBL() >= 2) {
            throw new IllegalArgumentException("Você só pode possuir dois planos do tipo PGBL.");
        }
        plans.add(plan);
    }

    private long countPlanVGBL() {
        return plans.stream()
                .filter(p -> p.typePlan == TypePlan.VGBL)
                .count();
    }

    private long countPlanPGBL() {
       return plans.stream()
                .filter(p -> p.typePlan == TypePlan.PGBL)
                .count();
    }

}
