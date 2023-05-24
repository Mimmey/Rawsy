package org.mimmey.controller.admin;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import org.mimmey.dto.response.admin.TrackAdminDto;
import org.mimmey.dto.response.admin.mapper.TrackAdminDtoMapper;
import org.mimmey.service.admin.AdminTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с профилями от лица администратора",
        version = "1.0.0"))
public class AdminTrackController {

    private final TrackAdminDtoMapper trackAdminDtoMapper;

    private final AdminTrackService adminTrackService;

    public AdminTrackController(@Autowired TrackAdminDtoMapper trackAdminDtoMapper,
                                @Autowired @Qualifier("admin-track") AdminTrackService adminTrackService) {
        this.trackAdminDtoMapper = trackAdminDtoMapper;
        this.adminTrackService = adminTrackService;
    }

    @Operation(
            summary = "Метод возвращает информацию о треке",
            parameters = {
                    @Parameter(name = "id", description = "Id трека", required = true)
            }
    )
    @PreAuthorize("hasAuthority('adminActions')")
    @RequestMapping(
            path = "tracks/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<TrackAdminDto> getTrack(@PathVariable("id") long id) {
        TrackAdminDto dto = trackAdminDtoMapper.toDto(adminTrackService.getTrack(id));
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Метод удаляет трек",
            parameters = {
                    @Parameter(name = "id", description = "Id трека", required = true)
            }
    )
    @PreAuthorize("hasAuthority('adminActions')")
    @RequestMapping(
            path = "tracks/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    public ResponseEntity<String> deleteTrack(@PathVariable("id") long id) {
        adminTrackService.deleteTrack(id);
        return ResponseEntity.ok("OK");
    }
}