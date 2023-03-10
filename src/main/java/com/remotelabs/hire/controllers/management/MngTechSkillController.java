package com.remotelabs.hire.controllers.management;

import com.remotelabs.hire.dtos.requests.AddTechnologyDto;
import com.remotelabs.hire.dtos.requests.UpdateTechnologyDto;
import com.remotelabs.hire.dtos.responses.TechnologyResource;
import com.remotelabs.hire.services.TechnologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.remotelabs.hire.constants.Constants.AUTH;

@RestController
@RequestMapping("/management/tech-skills")
@RequiredArgsConstructor
@Tag(name = "ManagementTechnologyController")
public class MngTechSkillController {

    private final TechnologyService technologyService;

    @PostMapping(value = "", headers = AUTH)
    @Operation(description = "Add tech skill")
    public ResponseEntity<Void> addTechSkill(@RequestBody AddTechnologyDto addTechnologyDto) {

        technologyService.addTechSkill(addTechnologyDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "", headers = AUTH)
    @Operation(summary = "Get the list of tech skills. ", description = "you can also filter by a keyword")
    public ResponseEntity<Page<TechnologyResource>> findTechSkills(@RequestParam int page,
                                                                   @RequestParam int size,
                                                                   @RequestParam(required = false) String keyword) {

        return new ResponseEntity<>(technologyService.findTechSkills(page, size, keyword), HttpStatus.OK);
    }

    @PutMapping(value = "/{techSkillId}", headers = AUTH)
    @Operation(description = "Admin update tech skill details")
    public ResponseEntity<Void> updateTechSkill(@PathVariable Long techSkillId,
                                                @RequestBody UpdateTechnologyDto updateTechnologyDto) {

        technologyService.updateTechnology(techSkillId, updateTechnologyDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{techSkillId}", headers = AUTH)
    @Operation(description = "Delete tech skill")
    public ResponseEntity<Void> deleteTechSkill(@PathVariable Long techSkillId) {

        technologyService.deleteTechnology(techSkillId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
