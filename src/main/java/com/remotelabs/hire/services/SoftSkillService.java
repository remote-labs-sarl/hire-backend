package com.remotelabs.hire.services;

import com.remotelabs.hire.converters.SoftSkillConverter;
import com.remotelabs.hire.dtos.requests.AddSoftSkillDto;
import com.remotelabs.hire.dtos.responses.SoftSkillResource;
import com.remotelabs.hire.entities.SoftSkill;
import com.remotelabs.hire.exceptions.HireInternalException;
import com.remotelabs.hire.repositories.SoftSkillRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoftSkillService {

    private final SoftSkillConverter softSkillConverter;
    private final SoftSkillRepository softSkillRepository;

    @Transactional
    public void addSoftSkill(AddSoftSkillDto addSoftSkillDto) {

        SoftSkill softSkill = new SoftSkill();
        softSkill.setName(addSoftSkillDto.getName());
        softSkillRepository.save(softSkill);
    }

    @Transactional
    public SoftSkillResource findSoftSkillById(Long softSkillId) {

        SoftSkill softSkill = softSkillRepository
                .findById(softSkillId).orElseThrow(() -> new HireInternalException("business skill not found"));

        return softSkillConverter.convert(softSkill);
    }

    @Transactional
    public Page<SoftSkillResource> findSoftSkills(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<SoftSkill> businessSkills = softSkillRepository.findAll(pageable);
        return businessSkills.map(softSkillConverter::convert);
    }

    @Transactional
    public void deleteSoftSkill(Long softSkillId){

        softSkillRepository.deleteById(softSkillId);
    }
}
