package com.remotelabs.hire.services;

import com.remotelabs.hire.converters.AdminConverter;
import com.remotelabs.hire.dtos.requests.AdminCreationDto;
import com.remotelabs.hire.dtos.requests.AdminUpdateDto;
import com.remotelabs.hire.dtos.responses.AdminResource;
import com.remotelabs.hire.entities.Admin;
import com.remotelabs.hire.entities.User;
import com.remotelabs.hire.enums.UserRole;
import com.remotelabs.hire.exceptions.HireInternalException;
import com.remotelabs.hire.repositories.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminConverter adminConverter;
    private final UserService userService;

    @Transactional
    public Page<AdminResource> getAdmins(int page, int size) {

        Page<Admin> admins = adminRepository.findAll(PageRequest.of(page, size));
        return admins.map(adminConverter::convert);
    }

    @Transactional
    public void addAdmin(AdminCreationDto adminCreationDto) {

        Admin admin = new Admin();
        admin.setFirstName(adminCreationDto.getFirstName());
        admin.setMiddleName(adminCreationDto.getMiddleName());
        admin.setLastName(adminCreationDto.getLastName());

        User user = userService.createUser(adminCreationDto.getLoginDetails(), UserRole.ADMIN);
        admin.setUser(user);
        adminRepository.save(admin);
    }

    @Transactional
    public void updateAdmin(Long adminId, AdminUpdateDto adminUpdateDto) {

        Admin admin = findById(adminId);
        admin.setFirstName(adminUpdateDto.getFirstName());
        admin.setMiddleName(adminUpdateDto.getMiddleName());
        admin.setLastName(adminUpdateDto.getLastName());
        adminRepository.save(admin);
    }


    @Transactional
    public void deleteAdmin(Long adminId) {

        adminRepository.deleteById(adminId);
    }
    private Admin findById(Long adminId) {

        return adminRepository.findById(adminId).orElseThrow(() -> new HireInternalException("Account not found"));
    }
}
