package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.update.TrackUpdateDto;
import org.mimmey.dto.response.TrackAuthorDto;
import org.mimmey.service.common.MyTrackService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("my/tracks")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с треками",
        version = "1.0.0"))
public class MyTrackController {

    private final MyTrackService myTrackService;

    @Operation(
            summary = "Метод возвращает трек",
            parameters = {
                    @Parameter(name = "trackId", description = "Id трека", required = true)
            }
    )
    @RequestMapping(
            path = "/track",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<TrackAuthorDto> getTrack(@RequestParam("trackId") long trackId) {
        return ResponseEntity.ok(myTrackService.getTrack(trackId));
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
    public ResponseEntity<String> changeTrack(@RequestBody TrackUpdateDto updates) {
        myTrackService.changeTrack(updates);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод скачивает архив с мультитреком",
            parameters = {
                    @Parameter(name = "trackId", description = "Id трека", required = true)
            }
    )
    @RequestMapping(
            path = "/download-multitrack",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<String> downloadMultitrack(@RequestParam("trackId") long trackId) {
        myTrackService.downloadMultitrack(trackId);
        return ResponseEntity.ok("OK");
    }
}