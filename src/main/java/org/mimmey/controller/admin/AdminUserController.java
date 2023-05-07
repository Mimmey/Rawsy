package org.mimmey.controller.admin;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.response.admin.TrackAdminDto;
import org.mimmey.dto.response.admin.UserInfoAdminDto;
import org.mimmey.service.admin.AdminUserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с профилями",
        version = "1.0.0"))
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Operation(
            summary = "Метод возвращает информацию о пользователе",
            parameters = {
                    @Parameter(name = "userId", description = "Id пользователя", required = true)
            }
    )
    @RequestMapping(
            path = "/user-info",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<UserInfoAdminDto> getUserInfo(@RequestParam("userId") long userId) {
        return ResponseEntity.ok(adminUserService.getUserInfo(userId));
    }

    @Operation(
            summary = "Метод отправляет пользователя в бан",
            parameters = {
                    @Parameter(name = "userId", description = "Id пользователя", required = true)
            }
    )
    @RequestMapping(
            path = "/ban",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> banUser(@RequestParam("userId") long userId) {
        adminUserService.banUser(userId);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод возвращает страницу списка подписчиков пользователя",
            parameters = {
                    @Parameter(name = "userId", description = "Id пользователя", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество подписчиков на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/subscribers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<UserInfoAdminDto>> getSubscriberList(@RequestParam("userId") long userId,
                                                                    @RequestParam("unitsOnPage") long unitsOnPage,
                                                                    @RequestParam("page") long page) {
        return ResponseEntity.ok(adminUserService.getSubscriberList(userId, unitsOnPage, page));
    }

    @Operation(
            summary = "Метод возвращает страницу списка аккаунтов-подписок пользователя",
            parameters = {
                    @Parameter(name = "userId", description = "Id пользователя", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество подписок на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/subscriptions",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<UserInfoAdminDto>> getSubscriptionList(@RequestParam("userId") long userId,
                                                                      @RequestParam("unitsOnPage") long unitsOnPage,
                                                                      @RequestParam("page") long page) {
        return ResponseEntity.ok(adminUserService.getSubscriptionList(userId, unitsOnPage, page));
    }

    @Operation(
            summary = "Метод возвращает страницу списка треков, опубликованных пользователем",
            parameters = {
                    @Parameter(name = "userId", description = "Id пользователя", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/published-tracks",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackAdminDto>> getPublishedTrackList(@RequestParam("userId") long userId,
                                                                     @RequestParam("unitsOnPage") long unitsOnPage,
                                                                     @RequestParam("page") long page) {
        return ResponseEntity.ok(adminUserService.getPublishedTrackList(userId, unitsOnPage, page));
    }
}