package com.remotelabs.hire.entities;

import com.remotelabs.hire.enums.Currency;
import com.remotelabs.hire.enums.NoticePeriodInterval;
import com.remotelabs.hire.enums.SalaryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "notice_periods")
public class NoticePeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NoticePeriodInterval interval;
}
