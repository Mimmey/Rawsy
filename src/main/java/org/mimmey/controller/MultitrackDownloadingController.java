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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(info = @Info(title = "RestController для работы с мультитреками",
        version = "1.0.0"))
public final class MultitrackDownloadingController {

    private final MultitrackDownloadingService multitrackDownloadingService;

    public MultitrackDownloadingController(@Autowired MultitrackDownloadingService multitrackDownloadingService) {
        this.multitrackDownloadingService = multitrackDownloadingService;
    }

    @Operation(
            summary = "Метод скачивает архив с мультитреком",
            parameters = {
                    @Parameter(name = "id", description = "Id трека", required = true)
            }
    )
    @RequestMapping(
            path = "track/{id}/multitrack/download",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> downloadMultitrack(@PathVariable("id") long id) {
        multitrackDownloadingService.downloadMultitrack(id);
        return ResponseEntity.ok("OK");
    }
}