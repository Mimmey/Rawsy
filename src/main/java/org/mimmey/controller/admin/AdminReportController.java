package org.mimmey.controller.admin;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import org.mimmey.dto.response.admin.ReportAdminDto;
import org.mimmey.dto.response.admin.TrackReportAdminDto;
import org.mimmey.dto.response.admin.UserReportAdminDto;
import org.mimmey.dto.response.admin.mapper.ReportAdminDtoMapper;
import org.mimmey.dto.response.admin.mapper.TrackReportAdminDtoMapper;
import org.mimmey.dto.response.admin.mapper.UserReportAdminDtoMapper;
import org.mimmey.service.admin.AdminReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с жалобами от лица администратора",
        version = "1.0.0"))
public final class AdminReportController {

    private final UserReportAdminDtoMapper userReportAdminDtoMapper;

    private final TrackReportAdminDtoMapper trackReportAdminDtoMapper;

    private final ReportAdminDtoMapper reportAdminDtoMapper;

    private final AdminReportService adminReportService;

    public AdminReportController(@Autowired UserReportAdminDtoMapper userReportAdminDtoMapper,
                                 @Autowired TrackReportAdminDtoMapper trackReportAdminDtoMapper,
                                 @Autowired ReportAdminDtoMapper reportAdminDtoMapper,
                                 @Autowired @Qualifier("admin-report") AdminReportService adminReportService) {
        this.userReportAdminDtoMapper = userReportAdminDtoMapper;
        this.trackReportAdminDtoMapper = trackReportAdminDtoMapper;
        this.reportAdminDtoMapper = reportAdminDtoMapper;
        this.adminReportService = adminReportService;
    }

    @Operation(
            summary = "Метод возвращает страницу списка жалоб на пользователя",
            parameters = {
                    @Parameter(name = "id", description = "Id пользователя", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество жалоб на странице", required = true)
            }
    )
    @PreAuthorize("hasAuthority('adminActions')")
    @RequestMapping(
            path = "/user/{id}/reports",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<UserReportAdminDto>> getUserReportList(@PathVariable("id") long id,
                                                                      @RequestParam("page") int page,
                                                                      @RequestParam("unitsOnPage") int unitsOnPage) {
        List<UserReportAdminDto> dtoList = userReportAdminDtoMapper.toDtoList(
                adminReportService.getUserReports(id, page - 1, unitsOnPage).stream().toList()
        );
        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод возвращает страницу списка жалоб на трек",
            parameters = {
                    @Parameter(name = "id", description = "Id трека", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество жалоб на странице", required = true)
            }
    )
    @RequestMapping(
            path = "/track/{id}/reports",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @PreAuthorize("hasAuthority('adminActions')")
    public ResponseEntity<List<TrackReportAdminDto>> getTrackReportList(@PathVariable("id") long id,
                                                                        @RequestParam("page") long page,
                                                                        @RequestParam("unitsOnPage") long unitsOnPage) {
        List<TrackReportAdminDto> dtoList = trackReportAdminDtoMapper.toDtoList(
                adminReportService.getTrackReports(id, page - 1, unitsOnPage).stream().toList()
        );
        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод возвращает жалобу",
            parameters = {
                    @Parameter(name = "id", description = "Id жалобы", required = true)
            }
    )
    @RequestMapping(
            path = "report/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @PreAuthorize("hasAuthority('adminActions')")
    public ResponseEntity<ReportAdminDto> getReport(@PathVariable("id") long id) {
        ReportAdminDto dto = reportAdminDtoMapper.toDto(adminReportService.getReport(id));
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Метод удаляет жалобу",
            parameters = {
                    @Parameter(name = "id", description = "Id жалобы", required = true)
            }
    )
    @RequestMapping(
            path = "report/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('adminActions')")
    public ResponseEntity<String> resolveReport(@PathVariable("id") long id) {
        adminReportService.resolveReport(id);
        return ResponseEntity.ok("OK");
    }
}
