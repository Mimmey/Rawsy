package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import org.mimmey.dto.request.creation.TrackReportCreationDto;
import org.mimmey.dto.request.creation.UserReportCreationDto;
import org.mimmey.dto.request.creation.mappers.TrackReportCreationDtoMapper;
import org.mimmey.dto.request.creation.mappers.UserReportCreationDtoMapper;
import org.mimmey.entity.associative.TrackReport;
import org.mimmey.entity.associative.UserReport;
import org.mimmey.service.common.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@OpenAPIDefinition(info = @Info(title = "RestController для работы с жалобами",
        version = "1.0.0"))
public class ReportController {

    private final UserReportCreationDtoMapper userReportCreationDtoMapper;

    private final TrackReportCreationDtoMapper trackReportCreationDtoMapper;

    private final ReportService reportService;

    public ReportController(@Autowired UserReportCreationDtoMapper userReportCreationDtoMapper,
                            @Autowired TrackReportCreationDtoMapper trackReportCreationDtoMapper,
                            @Autowired @Qualifier("common-report") ReportService reportService) {
        this.userReportCreationDtoMapper = userReportCreationDtoMapper;
        this.trackReportCreationDtoMapper = trackReportCreationDtoMapper;
        this.reportService = reportService;
    }

    @Operation(
            summary = "Метод отправляет жалобу на пользователя",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Жалоба:
                                        
                        userSubjectId — ID пользователя-объекта жалобы;
                        
                        content — содержание жалобы"""
            )
    )
    @RequestMapping(
            path = "/public/reports/user/publish",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @PreAuthorize("hasAuthority('beingAnAuthor')")
    public ResponseEntity<String> publishUserReport(@Valid @RequestBody UserReportCreationDto userReportDto) {
        UserReport userReport = userReportCreationDtoMapper.toEntity(userReportDto);
        reportService.createUserReport(userReport);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод отправляет жалобу на трек",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Жалоба:
                                        
                        trackSubjectId — ID трека-объекта жалобы;
                        
                        content — содержание жалобы"""
            )
    )
    @RequestMapping(
            path = "/public/reports/track/publish",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @PreAuthorize("hasAuthority('beingAnAuthor')")
    public ResponseEntity<String> publishTrackReport(@Valid @RequestBody TrackReportCreationDto trackReportDto) {
        TrackReport trackReport = trackReportCreationDtoMapper.toEntity(trackReportDto);
        reportService.createTrackReport(trackReport);
        return ResponseEntity.ok("OK");
    }
}
