package com.remotelabs.hire.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TechnologyResource {

    private Long id;
    private String name;
    private String logoUrl;
}
