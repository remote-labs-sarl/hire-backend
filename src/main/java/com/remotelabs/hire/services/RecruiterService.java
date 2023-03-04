package com.remotelabs.hire.services;

import com.remotelabs.hire.converters.RecruiterConverter;
import com.remotelabs.hire.dtos.requests.AddRecruiterDto;
import com.remotelabs.hire.dtos.requests.UpdateRecruiterDto;
import com.remotelabs.hire.dtos.responses.RecruiterResource;
import com.remotelabs.hire.entities.Recruiter;
import com.remotelabs.hire.entities.User;
import com.remotelabs.hire.enums.UserRole;
import com.remotelabs.hire.exceptions.HireInternalException;
import com.remotelabs.hire.repositories.RecruiterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final RecruiterConverter recruiterConverter;
    private final UserService userService;

    public Page<RecruiterResource> getAllRecruiters(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Recruiter> all = recruiterRepository.findAll(pageRequest);
        return all.map(recruiterConverter::convert);
    }

    @Transactional
    public void addRecruiter(AddRecruiterDto addRecruiterDto) {

        Recruiter recruiter = new Recruiter();
        recruiter.setFirstName(addRecruiterDto.getFirstName());
        recruiter.setMiddleName(addRecruiterDto.getMiddleName());
        recruiter.setLastName(addRecruiterDto.getLastName());

        User user = userService.createUser(addRecruiterDto.getLoginDetails(), UserRole.ADMIN);
        recruiter.setUser(user);
        recruiterRepository.save(recruiter);
    }

    @Transactional
    public void updateRecruiter(Long adminId, UpdateRecruiterDto adminUpdateDto) {

        Recruiter recruiter = findById(adminId);
        recruiter.setFirstName(adminUpdateDto.getFirstName());
        recruiter.setMiddleName(adminUpdateDto.getMiddleName());
        recruiter.setLastName(adminUpdateDto.getLastName());
        recruiterRepository.save(recruiter);
    }


    @Transactional
    public void deleteRecruiter(Long adminId) {

        recruiterRepository.deleteById(adminId);
    }
    private Recruiter findById(Long adminId) {

        return recruiterRepository.findById(adminId).orElseThrow(() -> new HireInternalException("Account not found"));
    }
}
