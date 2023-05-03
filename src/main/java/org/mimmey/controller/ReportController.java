package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.ReportDto;
import org.mimmey.entity.Report;
import org.mimmey.service.ReportService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("reports")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с жалобами",
        version = "1.0.0"))
public class ReportController {

    private final ReportService reportService;

    @Operation(
            summary = "Метод отправляет жалобу",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Жалоба: " +
                    "\nREQUEST_ID - Id заявки, "
                    //TODO ДОПИСАТЬ
            )
    )
    @RequestMapping(
            path = "/publish",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> publishReport(@RequestBody Report report) {
        reportService.createReport(report);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод возвращает список жалоб на пользователя",
            parameters = {
                    @Parameter(name = "userId", description = "Id пользователя", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество жалоб на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/user-reports",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<ReportDto>> getUserReportList(@RequestParam("userId") long userId,
                                                             @RequestParam("unitsOnPage") long unitsOnPage,
                                                             @RequestParam("page") long page) {
        return ResponseEntity.ok(reportService.getUserReportList(userId, unitsOnPage, page));
    }

    @Operation(
            summary = "Метод возвращает список жалоб на трек",
            parameters = {
                    @Parameter(name = "trackId", description = "Id трека", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество жалоб на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/track-reports",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<ReportDto>> getTrackReportList(@RequestParam("trackId") long trackId,
                                                              @RequestParam("unitsOnPage") long unitsOnPage,
                                                              @RequestParam("page") long page) {
        return ResponseEntity.ok(reportService.getTrackReportList(trackId, unitsOnPage, page));
    }

    @Operation(
            summary = "Метод возвращает жалобу",
            parameters = {
                    @Parameter(name = "reportId", description = "Id жалобы", required = true)
            }
    )
    @RequestMapping(
            path = "/report",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<ReportDto> getReport(@RequestParam("reportId") long reportId) {
        return ResponseEntity.ok(reportService.getReport(reportId));
    }

    @Operation(
            summary = "Метод удаляет жалобу",
            parameters = {
                    @Parameter(name = "reportId", description = "Id жалобы", required = true)
            }
    )
    @RequestMapping(
            path = "/resolve",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> publishReport(@RequestParam long reportId) {
        reportService.resolveReport(reportId);
        return ResponseEntity.ok("OK");
    }
}
