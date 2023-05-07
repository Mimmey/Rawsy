package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.creation.TrackCreationDto;
import org.mimmey.dto.response.TrackAuthorDto;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.dto.response.common.UserInfoCommonDto;
import org.mimmey.service.common.AuthorizedUserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("authorized-user")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с авторизованным пользователем",
        version = "1.0.0"))
public class AuthorizedUserController {

    private final AuthorizedUserService authorizedUserService;

    @Operation(
            summary = "Метод возвращает страницу списка подписок авторизованного пользователя",
            parameters = {
                    @Parameter(name = "unitsOnPage", description = "Количество подписок на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/subscriptions",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<UserInfoCommonDto>> getSubscriptionList(@RequestParam("unitsOnPage") long unitsOnPage,
                                                                       @RequestParam("page") long page) {
        return ResponseEntity.ok(authorizedUserService.getSubscriptionList(unitsOnPage, page));
    }

    @Operation(
            summary = "Метод возвращает страницу списка подписчиков авторизованного пользователя",
            parameters = {
                    @Parameter(name = "unitsOnPage", description = "Количество подписчиков на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/subscribers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<UserInfoCommonDto>> getSubscriberList(@RequestParam("unitsOnPage") long unitsOnPage,
                                                                     @RequestParam("page") long page) {
        return ResponseEntity.ok(authorizedUserService.getSubscriberList(unitsOnPage, page));
    }

    @Operation(
            summary = "Метод подписывает авторизованного пользователя на другого пользователя",
            parameters = {
                    @Parameter(name = "subscriptionUserId", description = "Id пользователя, на которого" +
                            "необходимо подписаться", required = true)
            }
    )
    @RequestMapping(
            path = "/subscribe",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> subscribe(@RequestParam("subscriptionUserId") long subscriptionUserId) {
        authorizedUserService.subscribe(subscriptionUserId);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод отписывает авторизованного пользователя от другого пользователя",
            parameters = {
                    @Parameter(name = "subscriptionUserId", description = "Id пользователя, от которого" +
                            "необходимо отписаться", required = true)
            }
    )
    @RequestMapping(
            path = "/unsubscribe",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> unsubscribe(@RequestParam("subscriptionUserId") long subscriptionUserId) {
        authorizedUserService.unsubscribe(subscriptionUserId);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод возвращает страницу списка треков, опубликованных авторизованным пользователем",
            parameters = {
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/published-tracks",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackAuthorDto>> getPublishedTrackList(@RequestParam("unitsOnPage") long unitsOnPage,
                                                                      @RequestParam("page") long page) {
        return ResponseEntity.ok(authorizedUserService.getPublishedTrackList(unitsOnPage, page));
    }

    @Operation(
            summary = "Метод возвращает страницу списка треков, приобретенных авторизованным пользователем",
            parameters = {
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/purchased-tracks",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackCommonDto>> getPurchasedTrackList(@RequestParam("unitsOnPage") long unitsOnPage,
                                                                      @RequestParam("page") long page) {
        return ResponseEntity.ok(authorizedUserService.getPurchasedTrackList(unitsOnPage, page));
    }

    @Operation(
            summary = "Метод возвращает страницу списка треков, находящихся в избранном у авторизованного пользователя",
            parameters = {
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/favourite-tracks",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackCommonDto>> getFavouriteTrackList(@RequestParam("unitsOnPage") long unitsOnPage,
                                                                      @RequestParam("page") long page) {
        return ResponseEntity.ok(authorizedUserService.getFavouriteTrackList(unitsOnPage, page));
    }

    @Operation(
            summary = "Метод возвращает страницу списка треков, находящихся в корзине у авторизованного пользователя",
            parameters = {
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/basket",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackCommonDto>> getBasketTrackList(@RequestParam("unitsOnPage") long unitsOnPage,
                                                                   @RequestParam("page") long page) {
        return ResponseEntity.ok(authorizedUserService.getBasketTrackList(unitsOnPage, page));
    }

    @Operation(
            summary = "Метод публикует трек",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Трек:\s
                        name — название трека;
                        typeId — ID типа трека;
                        about — описание трека;
                        invoice — инвойс (перечень того, какие версии трека входят в покупку);
                        hasVocal — присутствие вокала;
                        isCycled — зацикленность;
                        bpm — BPM трека;
                        cost — цена трека;
                        genreIds — список ID жанров трека;
                        moodIds — список ID настроений трека"""
            )
    )
    @RequestMapping(
            path = "/publish-track",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> publishTrack(@RequestBody TrackCreationDto trackCreationDto) {
        authorizedUserService.publishTrack(trackCreationDto);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод производит покупку трека",
            parameters = {
                    @Parameter(name = "trackId", description = "ID трека", required = true)
            }
    )
    @RequestMapping(
            path = "/purchase",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> purchaseTrack(@RequestParam("trackId") long trackId) {
        authorizedUserService.purchaseTrack(trackId);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод добавляет трек в избранное",
            parameters = {
                    @Parameter(name = "trackId", description = "ID трека", required = true)
            }
    )
    @RequestMapping(
            path = "/add-to-favourites",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> addTrackToFavorites(@RequestParam("trackId") long trackId) {
        authorizedUserService.addTrackToFavorites(trackId);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод добавляет трек в корзину",
            parameters = {
                    @Parameter(name = "trackId", description = "ID трека", required = true)
            }
    )
    @RequestMapping(
            path = "/add-to-basket",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> addTrackToBasket(@RequestParam("trackId") long trackId) {
        authorizedUserService.addTrackToBasket(trackId);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод удаляет опубликованный трек",
            parameters = {
                    @Parameter(name = "trackId", description = "ID трека", required = true)
            }
    )
    @RequestMapping(
            path = "/track",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    public ResponseEntity<String> deletePublishedTrack(@RequestParam("trackId") long trackId) {
        authorizedUserService.deletePublishedTrack(trackId);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод удаляет трек из избранного",
            parameters = {
                    @Parameter(name = "trackId", description = "ID трека", required = true)
            }
    )
    @RequestMapping(
            path = "/from-favourites",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    public ResponseEntity<String> removeTrackFromFavourites(@RequestParam("trackId") long trackId) {
        authorizedUserService.removeTrackFromFavourites(trackId);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод удаляет трек из корзины",
            parameters = {
                    @Parameter(name = "trackId", description = "ID трека", required = true)
            }
    )
    @RequestMapping(
            path = "/from-basket",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    public ResponseEntity<String> removeTrackFromBasket(@RequestParam("trackId") long trackId) {
        authorizedUserService.removeTrackFromBasket(trackId);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод очищает корзину"
    )
    @RequestMapping(
            path = "/clear-basket",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> clearBasket() {
        authorizedUserService.clearBasket();
        return ResponseEntity.ok("OK");
    }
}