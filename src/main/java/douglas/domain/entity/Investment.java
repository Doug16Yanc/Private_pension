package douglas.domain.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_investments")
public class Investment extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Date date;

    public Double amount;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    public Plan plan;
}
