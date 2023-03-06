package com.remotelabs.hire.controllers;

import com.remotelabs.hire.dtos.responses.JobRoleResource;
import com.remotelabs.hire.services.JobRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/job-roles")
@RequiredArgsConstructor
@Tag(name = "CountryController")
public class JobRoleController {

    private final JobRoleService jobRoleService;

    @GetMapping("")
    public ResponseEntity<Page<JobRoleResource>>getJobRoles(@RequestParam int page,@RequestParam int size){

        return new ResponseEntity<>(jobRoleService.getJobRoles(page, size), HttpStatus.OK);
    }
}
