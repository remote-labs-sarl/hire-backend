package com.remotelabs.hire.converters;

import com.remotelabs.hire.dtos.responses.BusinessSkillResource;
import com.remotelabs.hire.dtos.responses.SoftSkillResource;
import com.remotelabs.hire.entities.BusinessSkill;
import com.remotelabs.hire.entities.SoftSkill;
import com.remotelabs.hire.repositories.BusinessSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SoftSkillConverter {


   public SoftSkillResource convert(SoftSkill businessSkill){

       return SoftSkillResource.builder()
               .id(businessSkill.getId())
               .name(businessSkill.getName())
               .build();
   }
}
