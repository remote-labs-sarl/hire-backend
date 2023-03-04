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

import static com.remotelabs.hire.constants.Constants.AUTHORISATION;

@RestController
@RequestMapping("/management/technologies")
@RequiredArgsConstructor
@Tag(name = "ManagementTechnologyController")
public class MngTechnologyController {

    private final TechnologyService technologyService;

    @PostMapping(value = "", headers = AUTHORISATION)
    @Operation(description = "Admin adding a technology")
    public ResponseEntity<Void> addTechnology(@RequestBody AddTechnologyDto addTechnologyDto) {

        technologyService.addTechnology(addTechnologyDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "", headers = AUTHORISATION)
    @Operation(summary = "Get the list of technologies. ", description = "you can also filter by a keyword")
    public ResponseEntity<Page<TechnologyResource>> getTechnologies(@RequestParam int page,
                                                                    @RequestParam int size,
                                                                    @RequestParam(required = false) String keyword) {

        return new ResponseEntity<>(technologyService.getTechnologies(page, size, keyword), HttpStatus.OK);
    }

    @PutMapping(value = "/{technologyId}", headers = AUTHORISATION)
    @Operation(description = "Admin update technology details")
    public ResponseEntity<Void> updateTechnology(@PathVariable Long technologyId,
                                                 @RequestBody UpdateTechnologyDto updateTechnologyDto) {

        technologyService.updateTechnology(technologyId, updateTechnologyDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{technologyId}", headers = AUTHORISATION)
    @Operation(description = "Delete technology")
    public ResponseEntity<Void> deleteTechnology(@PathVariable Long technologyId) {

        technologyService.deleteTechnology(technologyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
