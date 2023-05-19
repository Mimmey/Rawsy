package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.mimmey.dto.response.common.CountryCommonDto;
import org.mimmey.dto.response.common.mapper.CountryCommonDtoMapper;
import org.mimmey.service.common.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@OpenAPIDefinition(info = @Info(title = "RestController для работы со странами",
        version = "1.0.0"))
public class CountryController {

    private final CountryCommonDtoMapper countryCommonDtoMapper;

    private final CountryService countryService;

    public CountryController(@Autowired CountryCommonDtoMapper countryCommonDtoMapper,
                             @Autowired CountryService countryService) {
        this.countryCommonDtoMapper = countryCommonDtoMapper;
        this.countryService = countryService;
    }

    @Operation(
            summary = "Метод возвращает список стран"
    )
    @RequestMapping(
            path = "/countries",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<CountryCommonDto>> getCountries() {
        List<CountryCommonDto> dtos = countryCommonDtoMapper.toDtoList(countryService.getCountries());
        return ResponseEntity.ok(dtos);
    }
}