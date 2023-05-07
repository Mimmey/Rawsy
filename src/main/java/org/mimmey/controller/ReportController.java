package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.creation.TrackReportCreationDto;
import org.mimmey.dto.request.creation.UserReportCreationDto;
import org.mimmey.service.common.ReportService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("reports")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с жалобами",
        version = "1.0.0"))
public class ReportController {

    private final ReportService reportService;

    @Operation(
            summary = "Метод отправляет жалобу на пользователя",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Жалоба:\s
                        userSubjectId — ID пользователя-объекта жалобы;
                        content — содержание жалобы"""
            )
    )
    @RequestMapping(
            path = "/publish",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> publishUserReport(@RequestBody UserReportCreationDto report) {
        reportService.createUserReport(report);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод отправляет жалобу на трек",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Жалоба:\s
                        trackSubjectId — ID пользователя-объекта жалобы;
                        content — содержание жалобы"""
            )
    )
    @RequestMapping(
            path = "/publish",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> publishTrackReport(@RequestBody TrackReportCreationDto report) {
        reportService.createTrackReport(report);
        return ResponseEntity.ok("OK");
    }
}
