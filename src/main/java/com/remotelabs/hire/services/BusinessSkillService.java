package com.remotelabs.hire.services;

import com.remotelabs.hire.converters.BusinessSkillConverter;
import com.remotelabs.hire.dtos.requests.AddBusinessSkillDto;
import com.remotelabs.hire.dtos.responses.BusinessSkillResource;
import com.remotelabs.hire.entities.BusinessSkill;
import com.remotelabs.hire.exceptions.HireInternalException;
import com.remotelabs.hire.repositories.BusinessSkillRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessSkillService {

    private final BusinessSkillConverter businessSkillConverter;
    private final BusinessSkillRepository businessSkillRepository;

    @Transactional
    public void addBusinessSkill(AddBusinessSkillDto addBusinessSkillDto) {

        BusinessSkill businessSkill = new BusinessSkill();
        businessSkill.setName(addBusinessSkillDto.getName());

        businessSkillRepository.save(businessSkill);
    }

    @Transactional
    public BusinessSkillResource findBusinessSkillById(Long businessSkillId) {

        BusinessSkill businessSkill = businessSkillRepository
                .findById(businessSkillId).orElseThrow(() -> new HireInternalException("business skill not found"));

        return businessSkillConverter.convert(businessSkill);
    }

    @Transactional
    public Page<BusinessSkillResource> findBusinessSkills(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BusinessSkill> businessSkills = businessSkillRepository.findAll(pageable);
        return businessSkills.map(businessSkillConverter::convert);
    }

    @Transactional
    public void deleteBusinessSkill(Long businessSkillId){

        businessSkillRepository.deleteById(businessSkillId);
    }
}
