package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.ExtendedUserInfoDto;
import org.mimmey.utils.Audio;
import org.mimmey.utils.Image;
import org.mimmey.service.ProfileSettingsService;
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
    public ResponseEntity<ExtendedUserInfoDto> getUserInfo() {
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
            summary = "Метод обновляет информацию о пользователе",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Обновляемые поля: " +
                    "\nUSER_ID - Id пользователя, "
                    //TODO ПЕРЕДЕЛАТЬ
            )
    )
    @RequestMapping(
            path = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PATCH
    )
    public ResponseEntity<String> updateUserInfo(@RequestBody ExtendedUserInfoDto updatedUserInfo) {
        profileSettingsService.updateUserInfo(updatedUserInfo);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод устанавливает новый джингл пользователя",
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
            summary = "Метод устанавливает новый аватар пользователя",
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

    @Operation(
            summary = "Метод добавляет новую медиа-ссылку в профиль пользователя",
            parameters = {
                    @Parameter(name = "link", description = "Медиа-ссылка", required = true)
            }
    )
    @RequestMapping(
            path = "/media-link",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> addMediaLink(@RequestParam("link") String link) {
        profileSettingsService.addMediaLink(link);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод удаляет медиа-ссылку из профиля пользователя",
            parameters = {
                    @Parameter(name = "linkIndex", description = "Порядковый номер ссылки", required = true)
            }
    )
    @RequestMapping(
            path = "/media-link",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    public ResponseEntity<String> removeMediaLink(@RequestParam("linkIndex") int linkIndex) {
        profileSettingsService.removeMediaLink(linkIndex);
        return ResponseEntity.ok("OK");
    }
}