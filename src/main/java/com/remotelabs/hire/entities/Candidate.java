package com.remotelabs.hire.entities;

import com.remotelabs.hire.enums.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String middleName;
    @Column(nullable = false)
    private String lastName;
    private String tags;
    @ManyToOne
    private Country country;
    @OneToOne(cascade = CascadeType.ALL)
    private SalaryExpectation salaryExpectation;
    private int yearsOfExperience;
    @OneToOne(cascade = CascadeType.ALL)
    private NoticePeriod noticePeriod;
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
}
