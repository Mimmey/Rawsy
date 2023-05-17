package org.mimmey.service.common.impl;

import lombok.RequiredArgsConstructor;
import org.mimmey.entity.Country;
import org.mimmey.repository.UserRepository;
import org.mimmey.service.common.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Country getCountry(int id) {
        return userRepository.findCountryById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getCountries() {
        return userRepository.findAllCountries();
    }
}
