package com.remotelabs.hire.services;

import com.remotelabs.hire.converters.CandidateConverter;
import com.remotelabs.hire.dtos.requests.AddCandidateDto;
import com.remotelabs.hire.dtos.requests.CandidateSearchDto;
import com.remotelabs.hire.dtos.responses.CandidateResource;
import com.remotelabs.hire.entities.Candidate;
import com.remotelabs.hire.entities.Country;
import com.remotelabs.hire.entities.Technology;
import com.remotelabs.hire.entities.User;
import com.remotelabs.hire.repositories.CandidateRepository;
import com.remotelabs.hire.repositories.criteria.CandidateCriteriaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CountryService countryService;
    private final TechnologyService technologyService;
    private final UserService userService;
    private final CandidateRepository candidateRepository;
    private final CandidateCriteriaRepository candidateCriteriaRepository;
    private final CandidateConverter candidateConverter;

    @Transactional
    public Page<CandidateResource> getCandidates(CandidateSearchDto candidateSearchDto, int page, int size) {

        Page<Candidate> candidates = candidateCriteriaRepository.findCandidatesByFilter(candidateSearchDto, page, size);
        return candidates.map(candidateConverter::convert);
    }

    @Transactional
    public void addCandidate(AddCandidateDto addCandidateDto) {

        Country country = countryService.findById(addCandidateDto.getCountryId());

        User user = userService.createUser(addCandidateDto.getUser());

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

        List<Technology>additionalTechnologies = technologyService
                .findByIds(addCandidateDto.getAdditionalTechnologiesIds());
        candidate.setAdditionalTechnologies(additionalTechnologies);

        candidateRepository.save(candidate);
    }
}
