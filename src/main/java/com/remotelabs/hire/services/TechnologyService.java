package com.remotelabs.hire.services;

import com.remotelabs.hire.entities.Technology;
import com.remotelabs.hire.exceptions.HireException;
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

    @Transactional
    public Page<Technology> getTechnologies(int page, int size, String keyword) {

        if (StringUtils.isEmpty(keyword)) {
            keyword = "";
        }
        Pageable pageable = PageRequest.of(page, size);
        return technologyRepository.findTechnologies(keyword, pageable);
    }

    public Technology findById(Long technologyId) {

        return technologyRepository
                .findById(technologyId)
                .orElseThrow(() -> new HireException("Technology not found with id " + technologyId));
    }

    public List<Technology> findByIds(List<Long> technologiesIds){

        return technologyRepository.findByIds(technologiesIds);
    }
}
