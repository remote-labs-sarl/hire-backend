package com.remotelabs.hire.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "recruiters")
public class Recruiter {

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
    @ManyToOne
    private Company company;
    @CreationTimestamp
    private Date creationDate;
}
