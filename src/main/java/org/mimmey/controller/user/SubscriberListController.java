package org.mimmey.controller.user;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("subscribers")
@OpenAPIDefinition(info = @Info(title = "RestController для работы со списком подписчиков",
        version = "1.0.0"))
public class SubscriberListController implements RelatedUserListController {

    @Qualifier("subscriberList")
    private final RelatedUserListService userListService;

    @Operation(
            summary = "Метод возвращает список кратких описаний подписчиков пользователя",
            parameters = {
                    @Parameter(name = "userId", description = "Id пользователя", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество подписчиков на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/list",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @Override
    public ResponseEntity<List<ShortUserInfoDto>> getList(@RequestParam("userId") long userId,
                                                          @RequestParam("unitsOnPage") long unitsOnPage,
                                                          @RequestParam("page") long page) {
        return ResponseEntity.ok(userListService.getList(userId, unitsOnPage, page));
    }

    @Operation(
            summary = "Метод удаляет пользователя из списка подписчиков",
            parameters = {
                    @Parameter(name = "userId", description = "Id владельца страницы", required = true)
                    @Parameter(name = "relatedUserId", description = "Id подписчика", required = true)
            }
    )
    @RequestMapping(
            path = "/remove",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @Override
    public ResponseEntity<String> deleteFromList(long userId, long relatedUserId) {
        userListService.deleteFromList(userId, relatedUserId);
        return ResponseEntity.ok("OK");
    }
}
