package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.mimmey.dto.request.creation.UserCreationDto;
import org.mimmey.dto.request.creation.mappers.UserCreationDtoMapper;
import org.mimmey.service.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с аутентификацией",
        version = "1.0.0"))
public class AuthController {

    private final UserCreationDtoMapper userCreationDtoMapper;

    private final UserService userService;

    public AuthController(@Autowired UserCreationDtoMapper userCreationDtoMapper,
                          @Autowired @Qualifier("common-user") UserService userService) {
        this.userCreationDtoMapper = userCreationDtoMapper;
        this.userService = userService;
    }

    @Operation(
            summary = "Метод регистрирует нового пользователя",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Пользователь:
                                        
                        
                        nickname — никнейм пользователя;
                        
                        email — адрес электронной почты;
                        
                        countryId — ID страны, в которой пользователь проживает;
                        
                        about — краткое описание пользователя;
                        
                        mediaLinks — ссылки на социальные сети"""
            )
    )
    @RequestMapping(
            path = "/register",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> register(@RequestBody UserCreationDto userCreationDto) {
        userService.createUser(userCreationDtoMapper.toEntity(userCreationDto));
        return ResponseEntity.ok("OK");
    }
}
