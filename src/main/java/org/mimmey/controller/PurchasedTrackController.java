package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.service.common.PurchasedTrackService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("purchased/tracks")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с треками",
        version = "1.0.0"))
public class PurchasedTrackController {

    private final PurchasedTrackService purchasedTrackService;

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
        purchasedTrackService.downloadMultitrack(trackId);
        return ResponseEntity.ok("OK");
    }
}