package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import org.mimmey.service.common.MultitrackDownloadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("multitrack")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с мультитреками",
        version = "1.0.0"))
public class MultitrackDownloadingController {

    private final MultitrackDownloadingService multitrackDownloadingService;

    public MultitrackDownloadingController(@Autowired MultitrackDownloadingService multitrackDownloadingService) {
        this.multitrackDownloadingService = multitrackDownloadingService;
    }

    @Operation(
            summary = "Метод скачивает архив с мультитреком",
            parameters = {
                    @Parameter(name = "trackId", description = "Id трека", required = true)
            }
    )
    @RequestMapping(
            path = "/download",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> downloadMultitrack(@RequestParam("trackId") long trackId) {
        multitrackDownloadingService.downloadMultitrack(trackId);
        return ResponseEntity.ok("OK");
    }
}