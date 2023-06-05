package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.mimmey.dto.response.common.TrackGenreCommonDto;
import org.mimmey.dto.response.common.mapper.TrackGenreCommonDtoMapper;
import org.mimmey.service.common.TrackGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@OpenAPIDefinition(info = @Info(title = "RestController для работы с жанрами треков",
        version = "1.0.0"))
public class TrackGenreController {

    private final TrackGenreCommonDtoMapper trackGenreCommonDtoMapper;

    private final TrackGenreService trackGenreService;

    public TrackGenreController(@Autowired TrackGenreCommonDtoMapper trackGenreCommonDtoMapper,
                                @Autowired TrackGenreService trackGenreService) {
        this.trackGenreCommonDtoMapper = trackGenreCommonDtoMapper;
        this.trackGenreService = trackGenreService;
    }

    @Operation(
            summary = "Метод возвращает список жанров треков"
    )
    @RequestMapping(
            path = "/public/tracks/genres",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackGenreCommonDto>> getTrackGenres() {
        List<TrackGenreCommonDto> dtos = trackGenreCommonDtoMapper.toDtoList(trackGenreService.getGenres());
        return ResponseEntity.ok(dtos);
    }
}