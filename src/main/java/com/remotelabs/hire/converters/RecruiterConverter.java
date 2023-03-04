package com.remotelabs.hire.converters;

import com.remotelabs.hire.dtos.responses.RecruiterResource;
import com.remotelabs.hire.entities.Recruiter;
import org.springframework.stereotype.Component;

@Component
public class RecruiterConverter {

    public RecruiterResource convert(Recruiter admin){

        return RecruiterResource.builder()
                .firstName(admin.getFirstName())
                .createdOn(admin.getCreationDate())
                .email(admin.getUser().getEmail())
                .middleName(admin.getMiddleName())
                .lastName(admin.getLastName())
                .build();
    }
}
