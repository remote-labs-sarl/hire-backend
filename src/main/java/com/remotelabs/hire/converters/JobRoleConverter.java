package com.remotelabs.hire.converters;

import com.remotelabs.hire.dtos.responses.JobRoleResource;
import com.remotelabs.hire.entities.JobRole;
import org.springframework.stereotype.Component;

@Component
public class JobRoleConverter {

    public JobRoleResource convert(JobRole jobRole) {

        return JobRoleResource.builder()
                .id(jobRole.getId())
                .name(jobRole.getName())
                .build();
    }
}
