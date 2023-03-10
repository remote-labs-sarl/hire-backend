package com.remotelabs.hire.controllers.management;

import com.remotelabs.hire.dtos.requests.AddBusinessSkillDto;
import com.remotelabs.hire.dtos.responses.BusinessSkillResource;
import com.remotelabs.hire.services.BusinessSkillService;
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
@RequestMapping("/management/business-skills")
@RequiredArgsConstructor
@Tag(name = "ManagementBusinessSkillsController")
public class MngBusinessSkillController {

    private final BusinessSkillService businessSkillService;

    @PostMapping(value = "", headers = AUTH)
    @Operation(description = "Add business skill")
    public ResponseEntity<Void> createBusinessSkill(@RequestBody @Valid AddBusinessSkillDto addBusinessSkillDto) {

        businessSkillService.addBusinessSkill(addBusinessSkillDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{businessSkillId}", headers = AUTH)
    @Operation(description = "Find business skill by id")
    public ResponseEntity<BusinessSkillResource> findById(@PathVariable Long businessSkillId) {

        return new ResponseEntity<>(businessSkillService.findBusinessSkillById(businessSkillId), HttpStatus.OK);
    }

    @GetMapping(value = "", headers = AUTH)
    @Operation(description = "Find business skills paginated")
    public ResponseEntity<Page<BusinessSkillResource>> findPaginated(int page, int size) {

        return new ResponseEntity<>(businessSkillService.findBusinessSkills(page, size), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{businessSkillId}", headers = AUTH)
    @Operation(description = "Delete business skill by id")
    public ResponseEntity<Void> deletedBusinessSkill(@PathVariable Long businessSkillId) {

        businessSkillService.deleteBusinessSkill(businessSkillId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
