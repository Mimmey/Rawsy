package org.mimmey.controller.admin;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import org.mimmey.dto.response.admin.TrackAdminDto;
import org.mimmey.dto.response.admin.UserInfoAdminDto;
import org.mimmey.dto.response.admin.mapper.TrackAdminDtoMapper;
import org.mimmey.dto.response.admin.mapper.UserInfoAdminDtoMapper;
import org.mimmey.service.admin.AdminUserService;
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
@OpenAPIDefinition(info = @Info(title = "RestController для работы с профилями от лица администратора",
        version = "1.0.0"))
public final class AdminUserController {

    private final UserInfoAdminDtoMapper userInfoAdminDtoMapper;

    private final TrackAdminDtoMapper trackAdminDtoMapper;

    private final AdminUserService adminUserService;

    public AdminUserController(@Autowired UserInfoAdminDtoMapper userInfoAdminDtoMapper,
                               @Autowired TrackAdminDtoMapper trackAdminDtoMapper,
                               @Autowired @Qualifier("admin-user") AdminUserService adminUserService) {
        this.userInfoAdminDtoMapper = userInfoAdminDtoMapper;
        this.trackAdminDtoMapper = trackAdminDtoMapper;
        this.adminUserService = adminUserService;
    }

    @Operation(
            summary = "Метод возвращает информацию о пользователе",
            parameters = {
                    @Parameter(name = "id", description = "Id пользователя", required = true)
            }
    )
    @PreAuthorize("hasAuthority('adminActions')")
    @RequestMapping(
            path = "user/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<UserInfoAdminDto> getUser(@PathVariable("id") long id) {
        UserInfoAdminDto dto = userInfoAdminDtoMapper.toDto(adminUserService.getUser(id));
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Метод отправляет пользователя в бан",
            parameters = {
                    @Parameter(name = "id", description = "Id пользователя", required = true)
            }
    )
    @PreAuthorize("hasAuthority('adminActions')")
    @RequestMapping(
            path = "user/{id}/ban",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> banUser(@PathVariable("id") long id) {
        adminUserService.banUser(id);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод возвращает страницу списка подписчиков пользователя",
            parameters = {
                    @Parameter(name = "id", description = "Id пользователя", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество подписчиков на странице", required = true)
            }
    )
    @PreAuthorize("hasAuthority('adminActions')")
    @RequestMapping(
            path = "user/{id}/subscribers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<UserInfoAdminDto>> getSubscriberList(@PathVariable("id") long id,
                                                                    @RequestParam("page") int page,
                                                                    @RequestParam("unitsOnPage") int unitsOnPage) {
        List<UserInfoAdminDto> dtoList = userInfoAdminDtoMapper.toDtoList(
                adminUserService.getSubscribers(id, page - 1, unitsOnPage).stream().toList()
        );
        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод возвращает страницу списка аккаунтов-подписок пользователя",
            parameters = {
                    @Parameter(name = "id", description = "Id пользователя", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество подписок на странице", required = true)
            }
    )
    @RequestMapping(
            path = "user/{id}/subscriptions",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @PreAuthorize("hasAuthority('adminActions')")
    public ResponseEntity<List<UserInfoAdminDto>> getSubscriptionList(@PathVariable("id") long id,
                                                                      @RequestParam("page") int page,
                                                                      @RequestParam("unitsOnPage") int unitsOnPage) {
        List<UserInfoAdminDto> dtoList = userInfoAdminDtoMapper.toDtoList(
                adminUserService.getSubscriptions(id, page - 1, unitsOnPage).stream().toList()
        );
        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод возвращает страницу списка треков, опубликованных пользователем",
            parameters = {
                    @Parameter(name = "id", description = "Id пользователя", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true)
            }
    )
    @RequestMapping(
            path = "user/{id}/published-tracks",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @PreAuthorize("hasAuthority('adminActions')")
    public ResponseEntity<List<TrackAdminDto>> getPublishedTrackList(@PathVariable("id") long id,
                                                                     @RequestParam("page") int page,
                                                                     @RequestParam("unitsOnPage") int unitsOnPage) {
        List<TrackAdminDto> dtoList = trackAdminDtoMapper.toDtoList(
                adminUserService.getPublishedTracks(id, page - 1, unitsOnPage).stream().toList()
        );
        return ResponseEntity.ok(dtoList);
    }
}