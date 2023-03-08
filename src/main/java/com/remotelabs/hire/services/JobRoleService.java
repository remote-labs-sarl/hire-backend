package com.remotelabs.hire.services;

import com.remotelabs.hire.converters.JobRoleConverter;
import com.remotelabs.hire.dtos.requests.AddJobRole;
import com.remotelabs.hire.dtos.requests.UpdateJobRole;
import com.remotelabs.hire.dtos.responses.JobRoleResource;
import com.remotelabs.hire.entities.JobRole;
import com.remotelabs.hire.exceptions.HireInternalException;
import com.remotelabs.hire.repositories.JobRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobRoleService {

    private final JobRoleRepository jobRoleRepository;
    private final JobRoleConverter jobRoleConverter;

    @Transactional
    public Page<JobRoleResource> getJobRoles(int page, int size) {

        return jobRoleRepository.findAll(PageRequest.of(0, 100)).map(jobRoleConverter::convert);
    }

    @Transactional
    public void addJobRole(AddJobRole addJobRole) {

        JobRole jobRole = new JobRole();
        jobRole.setName(addJobRole.getName());
        jobRoleRepository.save(jobRole);
    }

    @Transactional
    public void updateJobRole(Long jobRoleId, UpdateJobRole updateJobRole) {

        JobRole jobRole = jobRoleRepository.findById(jobRoleId)
                .orElseThrow(() -> new HireInternalException("Job role not found"));
        jobRole.setName(updateJobRole.getName());
        jobRoleRepository.save(jobRole);
    }

    @Transactional
    public void deleteJobRole(Long jobRoleId){
        jobRoleRepository.deleteById(jobRoleId);
    }
}
