package com.remotelabs.hire.converters;


import com.remotelabs.hire.dtos.responses.TechnologyResource;
import com.remotelabs.hire.entities.Technology;
import org.springframework.stereotype.Component;

@Component
public class TechnologyToResourceConverter {


    public TechnologyResource convert(Technology technology){

        return TechnologyResource.builder()
                .name(technology.getName())
                .logoUrl(technology.getLogoUrl())
                .id(technology.getId())
                .build();
    }
}
