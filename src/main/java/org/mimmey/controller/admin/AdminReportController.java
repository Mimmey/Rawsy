package org.mimmey.controller.admin;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.creation.ReportCreationDto;
import org.mimmey.dto.response.admin.ReportAdminDto;
import org.mimmey.service.admin.AdminReportService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/reports")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с жалобами",
        version = "1.0.0"))
public class AdminReportController {

    private final AdminReportService adminReportService;

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
    public ResponseEntity<List<ReportCreationDto>> getUserReportList(@RequestParam("userId") long userId,
                                                                     @RequestParam("unitsOnPage") long unitsOnPage,
                                                                     @RequestParam("page") long page) {
        return ResponseEntity.ok(adminReportService.getUserReportList(userId, unitsOnPage, page));
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
    public ResponseEntity<List<ReportCreationDto>> getTrackReportList(@RequestParam("trackId") long trackId,
                                                                      @RequestParam("unitsOnPage") long unitsOnPage,
                                                                      @RequestParam("page") long page) {
        return ResponseEntity.ok(adminReportService.getTrackReportList(trackId, unitsOnPage, page));
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
    public ResponseEntity<ReportAdminDto> getReport(@RequestParam("reportId") long reportId) {
        return ResponseEntity.ok(adminReportService.getReport(reportId));
    }

    @Operation(
            summary = "Метод удаляет жалобу",
            parameters = {
                    @Parameter(name = "reportId", description = "Id жалобы", required = true)
            }
    )
    @RequestMapping(
            path = "/report",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    public ResponseEntity<String> resolveReport(@RequestParam long reportId) {
        adminReportService.resolveReport(reportId);
        return ResponseEntity.ok("OK");
    }
}
