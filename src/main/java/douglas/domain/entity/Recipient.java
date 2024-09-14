package douglas.domain.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_recipients")
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    public String name;

    public String relationship;

    public String email;

    public String phone;

    public Address address;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer customer;
}
