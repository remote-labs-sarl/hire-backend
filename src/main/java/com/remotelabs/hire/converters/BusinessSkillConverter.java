package com.remotelabs.hire.converters;

import com.remotelabs.hire.dtos.responses.BusinessSkillResource;
import com.remotelabs.hire.entities.BusinessSkill;
import com.remotelabs.hire.repositories.BusinessSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BusinessSkillConverter {

   public BusinessSkillResource convert(BusinessSkill businessSkill){

       return BusinessSkillResource.builder()
               .id(businessSkill.getId())
               .name(businessSkill.getName())
               .build();
   }
}
