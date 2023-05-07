package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.dto.response.common.UserInfoCommonDto;
import org.mimmey.service.common.UserService;
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
public class UserController {

    private final UserService userService;

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
    public ResponseEntity<UserInfoCommonDto> getUserInfo(@RequestParam("userId") long userId) {
        return ResponseEntity.ok(userService.getUserInfo(userId));
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
    public ResponseEntity<List<UserInfoCommonDto>> getSubscriberList(@RequestParam("userId") long userId,
                                                                     @RequestParam("unitsOnPage") long unitsOnPage,
                                                                     @RequestParam("page") long page) {
        return ResponseEntity.ok(userService.getSubscriberList(userId, unitsOnPage, page));
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
    public ResponseEntity<List<UserInfoCommonDto>> getSubscriptionList(@RequestParam("userId") long userId,
                                                                       @RequestParam("unitsOnPage") long unitsOnPage,
                                                                       @RequestParam("page") long page) {
        return ResponseEntity.ok(userService.getSubscriptionList(userId, unitsOnPage, page));
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
    public ResponseEntity<List<TrackCommonDto>> getPublishedTrackList(@RequestParam("userId") long userId,
                                                                      @RequestParam("unitsOnPage") long unitsOnPage,
                                                                      @RequestParam("page") long page) {
        return ResponseEntity.ok(userService.getPublishedTrackList(userId, unitsOnPage, page));
    }
}