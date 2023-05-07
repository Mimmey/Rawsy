package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.update.UserUpdateDto;
import org.mimmey.dto.response.UserInfoAuthorizedDto;
import org.mimmey.utils.Audio;
import org.mimmey.utils.Image;
import org.mimmey.service.common.ProfileSettingsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user-settings")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с настройками профиля",
        version = "1.0.0"))
public class ProfileSettingsController {

    private final ProfileSettingsService profileSettingsService;

    @Operation(
            summary = "Метод возвращает расширенную информацию об авторизованном пользователе"
    )
    @RequestMapping(
            path = "/user-info",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<UserInfoAuthorizedDto> getUserInfo() {
        return ResponseEntity.ok(profileSettingsService.getUserInfo());
    }

    @Operation(
            summary = "Метод удаляет профиль авторизованного пользователя"
    )
    @RequestMapping(
            path = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    public ResponseEntity<String> deleteUser() {
        profileSettingsService.deleteUser();
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод обновляет информацию об авторизованном пользователе",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Обновление информации о пользователе:\s
                        nickname — имя пользователя;
                        email — электронная почта пользователя;
                        password — пароль пользователя;
                        about — описание пользователя;
                        mediaLinks — ссылки на социальные сети
                        
                    Все поля опциональны"""
            )
    )
    @RequestMapping(
            path = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PATCH
    )
    public ResponseEntity<String> updateUserInfo(@RequestBody UserUpdateDto userUpdateDto) {
        profileSettingsService.updateUserInfo(userUpdateDto);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод устанавливает новый джингл авторизованного пользователя",
            parameters = {
                    @Parameter(name = "jingle", description = "Новый джингл", required = true)
            }
    )
    @RequestMapping(
            path = "/jingle",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> setJingle(@RequestParam("jingle") Audio jingle) {
        profileSettingsService.setJingle(jingle);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод устанавливает новый аватар авторизованного пользователя",
            parameters = {
                    @Parameter(name = "avatar", description = "Новый аватар", required = true)
            }
    )
    @RequestMapping(
            path = "/avatar",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> setAvatar(@RequestParam("avatar") Image avatar) {
        profileSettingsService.setAvatar(avatar);
        return ResponseEntity.ok("OK");
    }
}