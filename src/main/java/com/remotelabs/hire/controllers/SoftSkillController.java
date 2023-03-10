package com.remotelabs.hire.controllers;

import com.remotelabs.hire.dtos.responses.BusinessSkillResource;
import com.remotelabs.hire.dtos.responses.SoftSkillResource;
import com.remotelabs.hire.services.BusinessSkillService;
import com.remotelabs.hire.services.SoftSkillService;
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
@RequestMapping("/public/soft-skills")
@RequiredArgsConstructor
@Tag(name = "SoftSkillsController")
public class SoftSkillController {

    private final SoftSkillService softSkillService;

    @GetMapping(value = "")
    @Operation(description = "Find soft skills paginated")
    public ResponseEntity<Page<SoftSkillResource>> findPaginated(int page, int size) {

        return new ResponseEntity<>(softSkillService.findSoftSkills(page, size), HttpStatus.OK);
    }
}
