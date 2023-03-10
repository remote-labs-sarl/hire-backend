package com.remotelabs.hire.controllers;

import com.remotelabs.hire.dtos.responses.BusinessSkillResource;
import com.remotelabs.hire.services.BusinessSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/business-skills")
@RequiredArgsConstructor
@Tag(name = "BusinessSkillsController")
public class BusinessSkillController {

    private final BusinessSkillService businessSkillService;

    @GetMapping(value = "")
    @Operation(description = "Find business skills paginated")
    public ResponseEntity<Page<BusinessSkillResource>> findPaginated(int page, int size) {

        return new ResponseEntity<>(businessSkillService.findBusinessSkills(page, size), HttpStatus.OK);
    }
}
