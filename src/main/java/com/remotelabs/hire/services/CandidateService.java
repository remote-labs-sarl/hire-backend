package com.remotelabs.hire.services;

import com.remotelabs.hire.converters.CandidateConverter;
import com.remotelabs.hire.dtos.requests.AddCandidateDto;
import com.remotelabs.hire.dtos.requests.CandidateSkillsDto;
import com.remotelabs.hire.dtos.requests.SearchCandidateDto;
import com.remotelabs.hire.dtos.requests.UpdateCandidateDto;
import com.remotelabs.hire.dtos.responses.CandidateResource;
import com.remotelabs.hire.entities.*;
import com.remotelabs.hire.enums.UserRole;
import com.remotelabs.hire.exceptions.HireInternalException;
import com.remotelabs.hire.repositories.*;
import com.remotelabs.hire.repositories.criteria.CandidateCriteriaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final UserService userService;
    private final CountryRepository countryRepository;
    private final CandidateConverter candidateConverter;
    private final CandidateRepository candidateRepository;
    private final CandidateCriteriaRepository candidateCriteriaRepository;
    private final CandidateTechSkillRepository candidateTechSkillRepository;
    private final CandidateSoftSkillRepository candidateSoftSkillRepository;
    private final CandidateBusinessSkillRepository candidateBusinessSkillRepository;
    private final NoticePeriodRepository noticePeriodRepository;
    private final SalaryExpectationRepository salaryExpectationRepository;

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
        candidateRepository.save(candidate);

        NoticePeriod noticePeriod = new NoticePeriod();
        noticePeriod.setInterval(addCandidateDto.getNoticePeriod().getNoticePeriodInterval());
        noticePeriod.setAmount(addCandidateDto.getNoticePeriod().getAmount());
        candidate.setNoticePeriod(noticePeriod);

        SalaryExpectation salaryExpectation = new SalaryExpectation();
        salaryExpectation.setType(addCandidateDto.getSalaryExpectation().getSalaryType());
        salaryExpectation.setCurrency(addCandidateDto.getSalaryExpectation().getCurrency());
        salaryExpectation.setAmount(addCandidateDto.getSalaryExpectation().getAmount());

        candidate.setSalaryExpectation(salaryExpectation);

        setSkills(addCandidateDto.getCandidateSkillsDto(), candidate.getId());
    }
    @Transactional
    public void updateCandidate(Long candidateId, UpdateCandidateDto updateCandidateDto) {

        Candidate candidate = getById(candidateId);
        candidate.setTags(updateCandidateDto.getTags());
        candidate.setFirstName(updateCandidateDto.getFirstName());
        candidate.setMiddleName(updateCandidateDto.getMiddleName());
        candidate.setLastName(updateCandidateDto.getLastName());
        candidate.setLanguages(updateCandidateDto.getLanguages());

        SalaryExpectation salaryExpectation = candidate.getSalaryExpectation();
        salaryExpectation.setAmount(updateCandidateDto.getSalaryExpectation().getAmount());
        salaryExpectation.setType(updateCandidateDto.getSalaryExpectation().getSalaryType());
        salaryExpectation.setCurrency(updateCandidateDto.getSalaryExpectation().getCurrency());

        NoticePeriod noticePeriod = candidate.getNoticePeriod();
        noticePeriod.setAmount(updateCandidateDto.getNoticePeriod().getAmount());
        noticePeriod.setInterval(updateCandidateDto.getNoticePeriod().getNoticePeriodInterval());

        candidate.setNoticePeriod(noticePeriod);
        candidate.setSalaryExpectation(salaryExpectation);

        candidateRepository.save(candidate);
    }

    @Transactional
    public void deleteCandidate(Long candidateId) {

        candidateRepository.deleteById(candidateId);
    }

    private void setSkills(CandidateSkillsDto candidateSkillsDto, Long candidateId) {

        List<CandidateTechSkill> candidateTechSkills = new ArrayList<>();
        candidateSkillsDto.getCandidateTechSkills().forEach(techSkill -> {

            CandidateTechSkill candidateTechSkill = new CandidateTechSkill();
            candidateTechSkill.setCandidateId(candidateId);
            candidateTechSkill.setTechSkillId(techSkill.getTechSkillId());
            candidateTechSkill.setMain(techSkill.isMain());
            candidateTechSkill.setYearsOfExperience(techSkill.getYearsOfExperience());
            candidateTechSkills.add(candidateTechSkill);
        });

        List<CandidateBusinessSkill> candidateBusinessSkills = new ArrayList<>();
        candidateSkillsDto.getBusinessSkillsId().forEach(businessSkillId -> {

            CandidateBusinessSkill candidateBusinessSkill = new CandidateBusinessSkill();
            candidateBusinessSkill.setCandidateId(candidateId);
            candidateBusinessSkill.setBusinessSkillId(businessSkillId);
            candidateBusinessSkills.add(candidateBusinessSkill);
        });

        List<CandidateSoftSkill> candidateSoftSkills = new ArrayList<>();
        candidateSkillsDto.getSoftSkillIds().forEach(softSkillId -> {

            CandidateSoftSkill candidateSoftSkill = new CandidateSoftSkill();
            candidateSoftSkill.setCandidateId(candidateId);
            candidateSoftSkill.setSoftSkillId(softSkillId);
            candidateSoftSkills.add(candidateSoftSkill);
        });

        candidateTechSkillRepository.saveAll(candidateTechSkills);
        candidateBusinessSkillRepository.saveAll(candidateBusinessSkills);
        candidateSoftSkillRepository.saveAll(candidateSoftSkills);
    }

    private Candidate getById(Long candidateId) {

        return candidateRepository.findById(candidateId)
                .orElseThrow(() -> new HireInternalException("Account not found"));
    }
}
