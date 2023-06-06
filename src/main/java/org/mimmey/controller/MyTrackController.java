package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.mimmey.dto.multipart_dto.MultipartFileDto;
import org.mimmey.dto.request.update.TrackUpdateDto;
import org.mimmey.dto.request.update.mapper.TrackUpdateDtoMapper;
import org.mimmey.dto.response.special.TrackAuthorDto;
import org.mimmey.dto.response.special.mapper.TrackAuthorDtoMapper;
import org.mimmey.entity.Track;
import org.mimmey.service.special.MyTrackService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
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
            path = "/my/published/tracks/{id}",
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
            parameters = {
                    @Parameter(name = "id", description = "ID трека", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Обновления трека:
                                                                
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
                        
                        
                    Все поля опциональны"""
            )
    )
    @RequestMapping(
            path = "/my/published/tracks/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PATCH
    )
    @PreAuthorize("hasAuthority('beingAnAuthor')")
    public ResponseEntity<String> changeTrack(@PathVariable("id") Long id,
                                              @Valid @RequestBody TrackUpdateDto updates) {
        Track updatedTrack = trackUpdateDtoMapper.toEntity(updates);
        updatedTrack.setId(id);
        myTrackService.changeTrack(updatedTrack);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод устанавливает новое превью для заданного трека",
            parameters = {
                    @Parameter(name = "id", description = "ID трека", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Новое превью трека в формате wav"
            )
    )
    @RequestMapping(
            path = "/my/published/tracks/{id}/preview",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    @SneakyThrows
    public ResponseEntity<String> setPreview(@PathVariable("id") long id,
                                             @ModelAttribute MultipartFileDto preview) {
        myTrackService.setPreview(id, preview.getFile().getBytes());
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод устанавливает новый архив-мультитрек для заданного трека",
            parameters = {
                    @Parameter(name = "id", description = "ID трека", required = true),
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Новый мультитрек в формате zip-архива"
            )
    )
    @RequestMapping(
            path = "/my/published/tracks/{id}/multitrack",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    @SneakyThrows
    public ResponseEntity<String> setMultitrack(@PathVariable("id") long id,
                                                @ModelAttribute MultipartFileDto multitrack) {
        myTrackService.setMultitrack(id, multitrack.getFile().getBytes());
        return ResponseEntity.ok("OK");
    }
}