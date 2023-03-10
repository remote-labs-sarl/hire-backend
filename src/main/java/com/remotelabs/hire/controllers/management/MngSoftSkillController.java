package com.remotelabs.hire.controllers.management;

import com.remotelabs.hire.dtos.requests.AddBusinessSkillDto;
import com.remotelabs.hire.dtos.requests.AddSoftSkillDto;
import com.remotelabs.hire.dtos.responses.BusinessSkillResource;
import com.remotelabs.hire.dtos.responses.SoftSkillResource;
import com.remotelabs.hire.services.SoftSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.remotelabs.hire.constants.Constants.AUTH;

@RestController
@RequestMapping("/management/soft-skills")
@RequiredArgsConstructor
@Tag(name = "ManagementSoftSkillsController")
public class MngSoftSkillController {

    private final SoftSkillService softSkillService;

    @PostMapping(value = "", headers = AUTH)
    @Operation(description = "Add business skill")
    public ResponseEntity<Void> createBusinessSkill(@RequestBody @Valid AddSoftSkillDto addSoftSkillDto) {

        softSkillService.addSoftSkill(addSoftSkillDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{softSkillId}", headers = AUTH)
    @Operation(description = "Find soft skill by id")
    public ResponseEntity<SoftSkillResource> findById(@PathVariable Long softSkillId) {

        return new ResponseEntity<>(softSkillService.findSoftSkillById(softSkillId), HttpStatus.OK);
    }

    @GetMapping(value = "", headers = AUTH)
    @Operation(description = "Find soft skills paginated")
    public ResponseEntity<Page<SoftSkillResource>> findAll(int page, int size) {

        return new ResponseEntity<>(softSkillService.findSoftSkills(page, size), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{softSkillId}", headers = AUTH)
    @Operation(description = "Delete soft skill by id")
    public ResponseEntity<Void> deleteSoftSkill(@PathVariable Long softSkillId) {

        softSkillService.deleteSoftSkill(softSkillId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
