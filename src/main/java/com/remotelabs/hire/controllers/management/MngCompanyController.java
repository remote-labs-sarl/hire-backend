package com.remotelabs.hire.controllers.management;

import com.remotelabs.hire.dtos.requests.AddCompanyDto;
import com.remotelabs.hire.dtos.requests.UpdateCompanyDto;
import com.remotelabs.hire.dtos.responses.CompanyResource;
import com.remotelabs.hire.services.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.remotelabs.hire.constants.Constants.AUTH;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/management/companies")
@RequiredArgsConstructor
@Tag(name = "ManagementCompaniesController")
public class MngCompanyController {

    private final CompanyService companyService;

    @PostMapping(value = "", headers = AUTH)
    @Operation(description = "Admin add a company")
    public ResponseEntity<Void> addCompany(@RequestBody AddCompanyDto addCompanyDto) {

        companyService.addCompany(addCompanyDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping(value = "", headers = AUTH)
    @Operation(description = "Get Companies")
    public ResponseEntity<Page<CompanyResource>> getCompanies(@RequestParam int page, @RequestParam int size) {

        return new ResponseEntity<>(companyService.getCompanies(page, size), OK);
    }

    @PutMapping(value = "/{companyId}", headers = AUTH)
    @Operation(description = "Update company")
    public ResponseEntity<Void> updateCompany(@PathVariable Long companyId,
                                              @RequestBody UpdateCompanyDto updateCompanyDto) {

        companyService.updateCompany(companyId, updateCompanyDto);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping(value = "/{companyId}", headers = AUTH)
    @Operation(description = "Delete company")
    public ResponseEntity<Void>deleteCompany(@PathVariable Long companyId){

        companyService.deleteCompany(companyId);
        return new ResponseEntity<>(OK);
    }
}
