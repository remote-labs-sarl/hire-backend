package com.remotelabs.hire.services.resourceservices;

import com.remotelabs.hire.converters.TechnologyToResourceConverter;
import com.remotelabs.hire.dtos.responses.TechnologyResource;
import com.remotelabs.hire.entities.Technology;
import com.remotelabs.hire.services.TechnologyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TechnologyResourceService {

    private final TechnologyService technologyService;
    private final TechnologyToResourceConverter technologyToResourceConverter;

    @Transactional
    public Page<TechnologyResource> getTechnologies(int page, int size, String keyword) {

        Page<Technology> technologies = technologyService.getTechnologies(page, size, keyword);
        return technologies.map(technologyToResourceConverter::convert);
    }
}
