package douglas.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_customers")
public class Customer extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    public String name;

    public String cpf;

    public String email;

    public String phone;

    @Embedded
    public Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Plan> plans;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Recipient> recipients = new ArrayList<>();

    public void addRecipient(Recipient recipient) {
        if (recipients.size() > 1) {
            throw new IllegalArgumentException("O número máximo de beneficiários é um.");
        }
        this.recipients.add(recipient);
    }

}
