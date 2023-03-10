package com.remotelabs.hire.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String code;
    private String name;
    private String dialCode;
    private String currencyName;
    private String currencySymbol;
    private String currencyCode;
    private Date deletedAt;
    @Column(name = "has_postal_code")
    private boolean hasPostalCode;
    private boolean active;

    @CreationTimestamp
    private Date creationDate;

    public Country(boolean active){
        this.active = active;
    }

    public Country() {

    }
}
