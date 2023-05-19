package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.mimmey.dto.response.common.TrackMoodCommonDto;
import org.mimmey.dto.response.common.mapper.TrackMoodCommonDtoMapper;
import org.mimmey.service.common.TrackMoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@OpenAPIDefinition(info = @Info(title = "RestController для работы с настроениями треков",
        version = "1.0.0"))
public final class TrackMoodController {

    private final TrackMoodCommonDtoMapper trackMoodCommonDtoMapper;

    private final TrackMoodService trackMoodService;

    public TrackMoodController(@Autowired TrackMoodCommonDtoMapper trackMoodCommonDtoMapper,
                               @Autowired TrackMoodService trackMoodService) {
        this.trackMoodCommonDtoMapper = trackMoodCommonDtoMapper;
        this.trackMoodService = trackMoodService;
    }

    @Operation(
            summary = "Метод возвращает список настроений треков"
    )
    @RequestMapping(
            path = "/tracks/moods",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackMoodCommonDto>> getTrackMoods() {
        List<TrackMoodCommonDto> dtos = trackMoodCommonDtoMapper.toDtoList(trackMoodService.getMoods());
        return ResponseEntity.ok(dtos);
    }
}