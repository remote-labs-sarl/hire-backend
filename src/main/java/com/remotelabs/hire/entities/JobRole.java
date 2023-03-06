package com.remotelabs.hire.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "job_roles")
public class JobRole {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;
    private String name;

    @CreationTimestamp
    private Date creationDate;
}
