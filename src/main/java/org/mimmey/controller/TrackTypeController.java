package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.mimmey.dto.response.common.TrackTypeCommonDto;
import org.mimmey.dto.response.common.mapper.TrackTypeCommonDtoMapper;
import org.mimmey.service.common.TrackTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@OpenAPIDefinition(info = @Info(title = "RestController для работы с типами треков",
        version = "1.0.0"))
public class TrackTypeController {

    private final TrackTypeCommonDtoMapper trackTypeCommonDtoMapper;

    private final TrackTypeService trackTypeService;

    public TrackTypeController(@Autowired TrackTypeCommonDtoMapper trackTypeCommonDtoMapper,
                               @Autowired TrackTypeService trackTypeService) {
        this.trackTypeCommonDtoMapper = trackTypeCommonDtoMapper;
        this.trackTypeService = trackTypeService;
    }

    @Operation(
            summary = "Метод возвращает список типов треков"
    )
    @RequestMapping(
            path = "/tracks/types",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackTypeCommonDto>> getTrackTypes() {
        List<TrackTypeCommonDto> dtos = trackTypeCommonDtoMapper.toDtoList(trackTypeService.getTypes());
        return ResponseEntity.ok(dtos);
    }
}