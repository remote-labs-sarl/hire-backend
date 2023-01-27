package com.remotelabs.hire.services;

import com.remotelabs.hire.converters.TechnologyToResourceConverter;
import com.remotelabs.hire.dtos.TechnologyResource;
import com.remotelabs.hire.entities.Technology;
import com.remotelabs.hire.repositories.TechnologyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechnologyService {

    private final TechnologyRepository technologyRepository;
    private final TechnologyToResourceConverter technologyToResourceConverter;

    @Transactional
    public Page<TechnologyResource> getTechnologies(int page, int size, String keyword) {

        if (StringUtils.isEmpty(keyword)) {
            keyword = "";
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Technology> allTechnologies = technologyRepository.findTechnologies(keyword, pageable);
        return allTechnologies.map(technologyToResourceConverter::convert);
    }
}
