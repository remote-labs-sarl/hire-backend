package com.remotelabs.hire.entities;

import com.remotelabs.hire.enums.CandidateType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
    private String middleName;
    @Column(nullable = false)
    private String lastName;
    private String tags;
    @Enumerated(EnumType.STRING)
    private CandidateType type;
    @ManyToOne
    private Country country;
    private BigDecimal salaryExpectation;
    private int yearsOfExperience;
    private int noticePeriod;
    private String languages;

    @ManyToOne
    private Technology mainTechnology;
}
