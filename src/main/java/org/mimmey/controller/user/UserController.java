package org.mimmey.controller.user;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.UserInfoDto;
import org.mimmey.entity.Track;
import org.mimmey.entity.User;
import org.mimmey.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<UserInfoDto> getUserInfo(@RequestParam("userId") long userId) {
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }

    @Operation(
            summary = "Метод публикует трек от имени пользователя",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Трек: " +
            "\nREQUEST_ID - Id заявки, "
            //TODO ДОПИСАТЬ
            )
    )
    @RequestMapping(
            path = "/publish-track",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> publishTrack(@AuthenticationPrincipal User user, @RequestBody Track track) {
        userService.publishTrack(user.getId, track);
        return ResponseEntity.ok("OK");
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
        userService.banUser(userId);
        return ResponseEntity.ok("OK");
    }
}