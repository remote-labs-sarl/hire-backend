package com.remotelabs.hire.services;

import com.remotelabs.hire.converters.TechnologyConverter;
import com.remotelabs.hire.dtos.requests.AddTechnologyDto;
import com.remotelabs.hire.dtos.requests.UpdateTechnologyDto;
import com.remotelabs.hire.dtos.responses.TechnologyResource;
import com.remotelabs.hire.entities.Technology;
import com.remotelabs.hire.exceptions.HireInternalException;
import com.remotelabs.hire.repositories.TechnologyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnologyService {

    private final TechnologyRepository technologyRepository;
    private final TechnologyConverter technologyConverter;

    @Transactional
    public Page<TechnologyResource> getTechnologies(int page, int size, String keyword) {

        if (StringUtils.isEmpty(keyword)) {
            keyword = "";
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Technology> technologies = technologyRepository.findTechnologies(keyword, pageable);
        return technologies.map(technologyConverter::convert);
    }

    @Transactional
    public void addTechnology(AddTechnologyDto addTechnologyDto) {

        Technology technology = new Technology();
        technology.setTags(addTechnologyDto.getTags());
        technology.setName(addTechnologyDto.getName());
        technology.setLogoUrl(addTechnologyDto.getLogoUrl());

        technologyRepository.save(technology);
    }

    @Transactional
    public void updateTechnology(Long technologyId, UpdateTechnologyDto updateTechnologyDto) {

        Technology technology = findById(technologyId);
        technology.setLogoUrl(updateTechnologyDto.getLogoUrl());
        technology.setTags(updateTechnologyDto.getTags());
        technology.setName(updateTechnologyDto.getName());
        technologyRepository.save(technology);
    }

    @Transactional
    public void deleteTechnology(Long technologyId){

        technologyRepository.deleteById(technologyId);
    }

    @Transactional
    public Technology findById(Long technologyId) {

        return technologyRepository
                .findById(technologyId)
                .orElseThrow(() -> new HireInternalException("Technology not found with id " + technologyId));
    }

    @Transactional
    public List<Technology> findByIds(List<Long> technologiesIds) {

        return technologyRepository.findByIds(technologiesIds);
    }
}
