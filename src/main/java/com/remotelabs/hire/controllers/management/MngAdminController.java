package com.remotelabs.hire.controllers.management;

import com.remotelabs.hire.constants.Constants;
import com.remotelabs.hire.dtos.requests.AdminCreationDto;
import com.remotelabs.hire.dtos.requests.AdminUpdateDto;
import com.remotelabs.hire.dtos.responses.AdminResource;
import com.remotelabs.hire.services.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.remotelabs.hire.constants.Constants.AUTHORISATION;

@Validated
@RestController
@RequestMapping("/management/admins")
@RequiredArgsConstructor
@Tag(name = "ManagementAdminController")
public class MngAdminController {

    private final AdminService adminService;

    @GetMapping(value = "", headers = AUTHORISATION)
    @Operation(description = "Get all admins")
    public ResponseEntity<Page<AdminResource>> getAllAdmins(int page, int size) {

        return new ResponseEntity<>(adminService.getAdmins(page, size), HttpStatus.OK);
    }

    @PostMapping(value = "", headers = AUTHORISATION)
    @Operation(description = "Add an admin")
    public ResponseEntity<Void> addAdmin(@RequestBody @Valid AdminCreationDto adminCreationDto) {

        adminService.addAdmin(adminCreationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{adminId}", headers = AUTHORISATION)
    @Operation(description = "Update an admin")
    public ResponseEntity<Void> updateAdmin(@PathVariable Long adminId, @RequestBody @Valid AdminUpdateDto adminUpdateDto) {

        adminService.updateAdmin(adminId, adminUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{adminId}", headers = AUTHORISATION)
    @Operation(description = "Delete an admin")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
