package com.remotelabs.hire.dtos.requests;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTechnologyDto {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String logoUrl;
    private String tags;
}
