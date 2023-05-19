package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.update.TrackUpdateDto;
import org.mimmey.dto.request.update.mapper.TrackUpdateDtoMapper;
import org.mimmey.dto.response.special.TrackAuthorDto;
import org.mimmey.dto.response.special.mapper.TrackAuthorDtoMapper;
import org.mimmey.entity.Track;
import org.mimmey.service.special.MyTrackService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "RestController для работы с треками",
        version = "1.0.0"))
public class MyTrackController {

    private final TrackAuthorDtoMapper trackAuthorDtoMapper;

    private final TrackUpdateDtoMapper trackUpdateDtoMapper;

    private final MyTrackService myTrackService;

    @Operation(
            summary = "Метод возвращает трек, автором которого является авторизованный пользователь",
            parameters = {
                    @Parameter(name = "id", description = "Id трека", required = true)
            }
    )
    @RequestMapping(
            path = "my/track/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<TrackAuthorDto> getTrack(@PathVariable("id") long id) {
        TrackAuthorDto dto = trackAuthorDtoMapper.toDto(myTrackService.getTrack(id));
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Метод меняет информацию о треке",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Обновления трека:
                                        
                        id — ID трека;
                        
                        name — новое название трека;
                        
                        typeId — новый ID типа трека;
                        
                        about — новое описание трека;
                        
                        invoice — новый инвойс (перечень того, какие версии трека входят в покупку);
                        
                        hasVocal — новое значение присутствия вокала;
                        
                        isCycled — новое значение зацикленности;
                        
                        bpm — новые BPM трека;
                        
                        cost — новая стоимость трека;
                        
                        genreIds — новый список ID жанров трека;
                        
                        moodIds — новый список ID настроений трека.
                        
                        
                    Все поля, кроме ID трека, опциональны"""
            )
    )
    @RequestMapping(
            path = "/track",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PATCH
    )
    @PreAuthorize("hasAuthority('beingAnAuthor')")
    public ResponseEntity<String> changeTrack(@RequestBody TrackUpdateDto updates) {
        Track updatedTrack = trackUpdateDtoMapper.toEntity(updates);
        myTrackService.changeTrack(updatedTrack);
        return ResponseEntity.ok("OK");
    }
}