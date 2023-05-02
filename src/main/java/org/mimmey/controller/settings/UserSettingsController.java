package org.mimmey.controller.settings;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
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
public class UserSettingsController {

    private final UserSettingsService userSettingsService;

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
    public ResponseEntity<UserInfoDto> getUserInfo(@RequestParam("userId") long userId) {
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }

    @Operation(
            summary = "Метод обновляет информацию о пользователе",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Обновляемые поля: " +
                    "\nUSER_ID - Id пользователя, "
                    //TODO ПЕРЕДЕЛАТЬ
            )
    )
    @RequestMapping(
            path = "/user-info",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PATCH
    )
    public ResponseEntity<UserInfoDto> updateUserInfo(@RequestBody UserInfoDto userInfoDto) {
        return ResponseEntity.ok(userSettingsService.updateUserInfo(userInfoDto));
    }

    //TODO ДОПИСАТЬ
}