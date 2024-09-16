package douglas.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_recipients")
public class Recipient extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    public String cpf;

    public String relationship;

    public String email;

    public String phone;

    @Embedded
    public Address address;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    public Customer customer;

}
