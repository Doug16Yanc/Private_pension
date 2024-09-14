package douglas.domain.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    public int number;

    public String street;

    public String neighborhood;

    public String city;

    public String state;

    public String zip;
}
