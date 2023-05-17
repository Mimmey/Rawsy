package org.mimmey.service.common;

import org.mimmey.entity.Country;

import java.util.List;

/**
 * @author Olga Motyleva
 */
public interface CountryService {

    /**
     * The function that returns the country by its id
     *
     * @param id ID of the country
     */
    Country getCountry(int id);

    /**
     * The function that returns the list of countries
     */
    List<Country> getCountries();
}
