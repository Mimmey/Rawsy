package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import org.mimmey.dto.request.update.UserUpdateDto;
import org.mimmey.dto.request.update.mapper.UserUpdateDtoMapper;
import org.mimmey.dto.response.special.UserInfoAuthorizedDto;
import org.mimmey.dto.response.special.mapper.UserInfoAuthorizedDtoMapper;
import org.mimmey.entity.User;
import org.mimmey.service.special.ProfileSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("settings")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с настройками профиля",
        version = "1.0.0"))
public class ProfileSettingsController {

    private final UserInfoAuthorizedDtoMapper userInfoAuthorizedDtoMapper;

    private final UserUpdateDtoMapper userUpdateDtoMapper;

    private final ProfileSettingsService profileSettingsService;

    public ProfileSettingsController(@Autowired UserInfoAuthorizedDtoMapper userInfoAuthorizedDtoMapper,
                                     @Autowired UserUpdateDtoMapper userUpdateDtoMapper,
                                     @Autowired @Qualifier("settings-user") ProfileSettingsService profileSettingsService) {
        this.userInfoAuthorizedDtoMapper = userInfoAuthorizedDtoMapper;
        this.userUpdateDtoMapper = userUpdateDtoMapper;
        this.profileSettingsService = profileSettingsService;
    }

    @Operation(
            summary = "Метод возвращает расширенную информацию об авторизованном пользователе"
    )
    @RequestMapping(
            path = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<UserInfoAuthorizedDto> getUserInfo() {
        UserInfoAuthorizedDto dto = userInfoAuthorizedDtoMapper.toDto(profileSettingsService.getUser());
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Метод удаляет профиль авторизованного пользователя"
    )
    @RequestMapping(
            path = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> deleteUser() {
        profileSettingsService.deleteUser();
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод обновляет информацию об авторизованном пользователе",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Обновление информации о пользователе:
                                        
                        nickname — имя пользователя;
                                        
                        email — электронная почта пользователя;
                                        
                        password — пароль пользователя;
                                        
                        about — описание пользователя;
                                        
                        mediaLinks — ссылки на социальные сети.
                        
                        
                    Все поля опциональны"""
            )
    )
    @RequestMapping(
            path = "/user",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PATCH
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> updateUserInfo(@RequestBody UserUpdateDto userUpdateDto) {
        User updatedUser = userUpdateDtoMapper.toEntity(userUpdateDto);
        profileSettingsService.updateUser(updatedUser);
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
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> setJingle(@RequestParam("jingle") byte[] jingle) {
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
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> setAvatar(@RequestParam("avatar") byte[] avatar) {
        profileSettingsService.setAvatar(avatar);
        return ResponseEntity.ok("OK");
    }
}