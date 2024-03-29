package org.mimmey.service.common.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mimmey.entity.Country;
import org.mimmey.repository.CountryRepository;
import org.mimmey.service.common.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Country getCountry(int id) {
        return countryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }
}
