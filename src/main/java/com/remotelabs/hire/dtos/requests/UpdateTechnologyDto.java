package com.remotelabs.hire.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTechnologyDto {

    private String name;
    private String tags;
    private String logoUrl;
}
