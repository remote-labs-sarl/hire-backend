package com.remotelabs.hire.converters;


import com.remotelabs.hire.dtos.responses.TechnologyResource;
import com.remotelabs.hire.entities.TechSkill;
import org.springframework.stereotype.Component;

@Component
public class TechnologyConverter {
    public TechnologyResource convert(TechSkill techSkill){

        return TechnologyResource.builder()
                .name(techSkill.getName())
                .logoUrl(techSkill.getLogoUrl())
                .id(techSkill.getId())
                .build();
    }
}
