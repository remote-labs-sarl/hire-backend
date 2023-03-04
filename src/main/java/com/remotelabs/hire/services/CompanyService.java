package com.remotelabs.hire.services;

import com.remotelabs.hire.converters.CompanyConverter;
import com.remotelabs.hire.dtos.requests.AddCompanyDto;
import com.remotelabs.hire.dtos.requests.UpdateCompanyDto;
import com.remotelabs.hire.dtos.responses.CompanyResource;
import com.remotelabs.hire.entities.Company;
import com.remotelabs.hire.exceptions.HireInternalException;
import com.remotelabs.hire.repositories.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyConverter companyConverter;

    @Transactional
    public void addCompany(AddCompanyDto addCompanyDto) {

        Company company = new Company();
        company.setName(addCompanyDto.getName());
        companyRepository.save(company);
    }

    @Transactional
    public void updateCompany(Long companyId, UpdateCompanyDto updateCompanyDto) {

        Company company = findById(companyId);
        company.setName(updateCompanyDto.getName());
        companyRepository.save(company);
    }

    @Transactional
    public void deleteCompany(Long companyId){

        companyRepository.deleteById(companyId);
    }

    @Transactional
    public Page<CompanyResource> getCompanies(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<Company> companies = companyRepository.findAll(pageable);
        return companies.map(companyConverter::convert);
    }

    private Company findById(Long id) {

        return companyRepository.findById(id).orElseThrow(() -> new HireInternalException("Company not found"));
    }
}
