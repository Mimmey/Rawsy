package org.mimmey.service.common;

import org.mimmey.entity.Country;

import java.util.List;

/**
 * @author Olga Motyleva
 */
public interface CountryService {

    /**
     * The function that returns the country by its ID
     *
     * @param id ID of the country
     * @return the country with the given ID
     */
    Country getCountry(int id);

    /**
     * The function that returns the list of countries
     *
     * @return list of all countries in the DB
     */
    List<Country> getCountries();
}
