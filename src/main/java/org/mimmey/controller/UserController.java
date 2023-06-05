package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.dto.response.common.UserInfoCommonDto;
import org.mimmey.dto.response.common.mapper.TrackCommonDtoMapper;
import org.mimmey.dto.response.common.mapper.UserInfoCommonDtoMapper;
import org.mimmey.service.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@OpenAPIDefinition(info = @Info(title = "RestController для работы с профилями",
        version = "1.0.0"))
public class UserController {

    private final UserInfoCommonDtoMapper userInfoCommonDtoMapper;

    private final TrackCommonDtoMapper trackCommonDtoMapper;

    private final UserService userService;

    public UserController(@Autowired UserInfoCommonDtoMapper userInfoCommonDtoMapper,
                          @Autowired TrackCommonDtoMapper trackCommonDtoMapper,
                          @Autowired @Qualifier("common-user") UserService userService) {
        this.userInfoCommonDtoMapper = userInfoCommonDtoMapper;
        this.trackCommonDtoMapper = trackCommonDtoMapper;
        this.userService = userService;
    }

    @Operation(
            summary = "Метод возвращает информацию о пользователе",
            parameters = {
                    @Parameter(name = "id", description = "Id пользователя", required = true)
            }
    )
    @RequestMapping(
            path = "/public/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<UserInfoCommonDto> getUser(@PathVariable("id") long id) {
        UserInfoCommonDto dto = userInfoCommonDtoMapper.toDto(userService.getUser(id));
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Метод возвращает страницу списка подписчиков пользователя",
            parameters = {
                    @Parameter(name = "id", description = "Id пользователя", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество подписчиков на странице", required = true)
            }
    )
    @RequestMapping(
            path = "/public/users/{id}/subscribers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<UserInfoCommonDto>> getSubscribers(@PathVariable("id") long id,
                                                                  @RequestParam(value = "page", defaultValue = "1") int page,
                                                                  @RequestParam(value = "unitsOnPage", defaultValue = "2147483647") int unitsOnPage) {
        List<UserInfoCommonDto> dtoList = userInfoCommonDtoMapper.toDtoList(
                userService.getSubscribers(id, page - 1, unitsOnPage).stream().toList()
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
            path = "/public/users/{id}/subscriptions",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<UserInfoCommonDto>> getSubscriptions(@PathVariable("id") long id,
                                                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                                                    @RequestParam(value = "unitsOnPage", defaultValue = "2147483647") int unitsOnPage) {
        List<UserInfoCommonDto> dtoList = userInfoCommonDtoMapper.toDtoList(
                userService.getSubscriptions(id, page - 1, unitsOnPage).stream().toList()
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
            path = "/public/users/{id}/published/tracks",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackCommonDto>> getPublishedTracks(@PathVariable("id") long id,
                                                                   @RequestParam(value = "page", defaultValue = "1") int page,
                                                                   @RequestParam(value = "unitsOnPage", defaultValue = "2147483647") int unitsOnPage) {
        List<TrackCommonDto> dtoList = trackCommonDtoMapper.toDtoList(
                userService.getPublishedTracks(id, page - 1, unitsOnPage).stream().toList()
        );
        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод возвращает аватар пользователя",
            parameters = {
                    @Parameter(name = "id", description = "ID пользователя", required = true)
            }
    )
    @RequestMapping(
            path = "/public/users/{id}/avatar",
            produces = MediaType.IMAGE_JPEG_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<byte[]> getAvatar(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getAvatar(id));
    }

    @Operation(
            summary = "Метод возвращает джингл пользователя",
            parameters = {
                    @Parameter(name = "id", description = "Джингл пользователя", required = true)
            }
    )
    @RequestMapping(
            path = "/public/users/{id}/jingle",
            produces = "audio/wav",
            method = RequestMethod.GET
    )
    public ResponseEntity<byte[]> getJingle(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getJingle(id));
    }
}