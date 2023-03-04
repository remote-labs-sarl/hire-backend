package com.remotelabs.hire.converters;

import com.remotelabs.hire.dtos.responses.CompanyResource;
import com.remotelabs.hire.dtos.responses.RecruiterResource;
import com.remotelabs.hire.entities.Company;
import com.remotelabs.hire.entities.Recruiter;
import org.springframework.stereotype.Component;

@Component
public class CompanyConverter {

    public CompanyResource convert(Company company){

        return CompanyResource.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }
}
