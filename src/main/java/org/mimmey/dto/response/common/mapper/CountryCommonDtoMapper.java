package org.mimmey.dto.response.common.mapper;

import org.mapstruct.Mapper;
import org.mimmey.dto.response.common.CountryCommonDto;
import org.mimmey.entity.Country;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryCommonDtoMapper {

    CountryCommonDto toDto(Country country);

    List<CountryCommonDto> toDtoList(List<Country> countryList);
}