package com.remotelabs.hire.entities;

import com.remotelabs.hire.enums.CandidateType;
import com.remotelabs.hire.enums.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "candidates")
public class Admin {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String firstName;
    private String middleName;
    @Column(nullable = false)
    private String lastName;

    @OneToOne
    private User user;

    @CreationTimestamp
    private Date creationDate;

    @ElementCollection
    @CollectionTable(
            name = "candidate_language",
            joinColumns = @JoinColumn(name = "candidate_id"))
    @Enumerated(EnumType.STRING)
    private List<Language> languages;

    @ManyToOne
    private Technology mainTechnology;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "candidate_technology",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private List<Technology> additionalTechnologies;
}
