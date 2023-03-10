package com.remotelabs.hire.services;

import com.remotelabs.hire.converters.CandidateConverter;
import com.remotelabs.hire.dtos.requests.AddCandidateDto;
import com.remotelabs.hire.dtos.requests.SearchCandidateDto;
import com.remotelabs.hire.dtos.requests.UpdateCandidateDto;
import com.remotelabs.hire.dtos.responses.CandidateResource;
import com.remotelabs.hire.entities.Candidate;
import com.remotelabs.hire.entities.Country;
import com.remotelabs.hire.entities.Technology;
import com.remotelabs.hire.entities.User;
import com.remotelabs.hire.enums.UserRole;
import com.remotelabs.hire.exceptions.HireInternalException;
import com.remotelabs.hire.repositories.CandidateRepository;
import com.remotelabs.hire.repositories.CountryRepository;
import com.remotelabs.hire.repositories.criteria.CandidateCriteriaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CountryRepository countryRepository;
    private final TechnologyService technologyService;
    private final UserService userService;
    private final CandidateRepository candidateRepository;
    private final CandidateCriteriaRepository candidateCriteriaRepository;
    private final CandidateConverter candidateConverter;

    @Transactional
    public Page<CandidateResource> getCandidates(SearchCandidateDto searchCandidateDto, int page, int size) {

        Page<Candidate> candidates = candidateCriteriaRepository.findCandidatesByFilter(searchCandidateDto, page, size);
        return candidates.map(candidateConverter::convert);
    }

    @Transactional
    public void addCandidate(AddCandidateDto addCandidateDto) {

        Country country = countryRepository.findById(addCandidateDto.getCountryId())
                .orElseThrow(() -> new HireInternalException("Country not found"));

        User user = userService.createUser(addCandidateDto.getLoginDetails(), UserRole.CANDIDATE);

        Candidate candidate = new Candidate();
        candidate.setLanguages(addCandidateDto.getLanguages());
        candidate.setCountry(country);
        candidate.setTags(addCandidateDto.getTags());
        candidate.setFirstName(addCandidateDto.getFirstName());
        candidate.setMiddleName(addCandidateDto.getMiddleName());
        candidate.setLastName(addCandidateDto.getLastName());
        candidate.setUser(user);

        Technology mainTechnology = technologyService.findById(addCandidateDto.getMainTechnologyId());
        candidate.setMainTechnology(mainTechnology);

        List<Technology> additionalTechnologies = technologyService
                .findByIds(addCandidateDto.getAdditionalTechnologiesIds());
        candidate.setAdditionalTechnologies(additionalTechnologies);

        candidateRepository.save(candidate);
    }

    @Transactional
    public void updateCandidate(Long candidateId, UpdateCandidateDto updateCandidateDto) {

        Candidate candidate = getById(candidateId);
        candidate.setTags(updateCandidateDto.getTags());
        candidate.setFirstName(updateCandidateDto.getFirstName());
        candidate.setMiddleName(updateCandidateDto.getMiddleName());
        candidate.setLastName(updateCandidateDto.getLastName());
        candidate.setLanguages(updateCandidateDto.getLanguages());
        candidate.setNoticePeriod(updateCandidateDto.getNoticePeriod());
        candidate.setSalaryExpectation(updateCandidateDto.getSalaryExpectation());
        candidate.setType(updateCandidateDto.getCandidateRole());

        candidateRepository.save(candidate);
    }

    private Candidate getById(Long candidateId) {

        return candidateRepository.findById(candidateId)
                .orElseThrow(() -> new HireInternalException("Account not found"));
    }

    @Transactional
    public void deleteCandidate(Long candidateId) {

        candidateRepository.deleteById(candidateId);
    }
}
