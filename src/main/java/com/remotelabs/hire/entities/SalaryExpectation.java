package com.remotelabs.hire.entities;

import com.remotelabs.hire.enums.Currency;
import com.remotelabs.hire.enums.SalaryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "salary_expectations")
public class SalaryExpectation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SalaryType type;
}
