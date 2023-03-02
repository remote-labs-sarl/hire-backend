package com.remotelabs.hire.converters;

import com.remotelabs.hire.dtos.responses.AdminResource;
import com.remotelabs.hire.entities.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminConverter {

    public AdminResource convert(Admin admin){

        return AdminResource.builder()
                .firstName(admin.getFirstName())
                .createdOn(admin.getCreationDate())
                .email(admin.getUser().getEmail())
                .middleName(admin.getMiddleName())
                .lastName(admin.getLastName())
                .build();
    }
}
