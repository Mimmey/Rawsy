package org.mimmey.controller.user;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.controller.user.RelatedUserListController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("subscriptions")
@OpenAPIDefinition(info = @Info(title = "RestController для работы со списком подписок",
        version = "1.0.0"))
public class SubscriptionListController implements RelatedUserListController {

    @Qualifier("subscriptionList")
    private final RelatedUserListService userListService;

    @Operation(
            summary = "Метод возвращает список кратких описаний аккаунтов-подписок пользователя",
            parameters = {
                    @Parameter(name = "userId", description = "Id пользователя", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество подписок на странице", required = true),
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
            summary = "Метод отписывает ползователя от аккаунта-подписки",
            parameters = {
                    @Parameter(name = "userId", description = "Id владельца страницы", required = true)
                    @Parameter(name = "relatedUserId", description = "Id аккаунта-подписки", required = true)
            }
    )
    @RequestMapping(
            path = "/unsubscribe",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @Override
    public ResponseEntity<String> deleteFromList(long userId, long relatedUserId) {
        userListService.deleteFromList(userId, relatedUserId);
        return ResponseEntity.ok("OK");
    }
}
