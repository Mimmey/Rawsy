package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import org.mimmey.dto.request.creation.TrackCreationDto;
import org.mimmey.dto.request.creation.mappers.TrackCreationDtoMapper;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.dto.response.common.UserInfoCommonDto;
import org.mimmey.dto.response.common.mapper.TrackCommonDtoMapper;
import org.mimmey.dto.response.common.mapper.UserInfoCommonDtoMapper;
import org.mimmey.dto.response.special.TrackAuthorDto;
import org.mimmey.dto.response.special.mapper.TrackAuthorDtoMapper;
import org.mimmey.entity.Track;
import org.mimmey.service.special.AuthorizedUserService;
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

import java.util.List;

@RestController
@RequestMapping("authorized")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с авторизованным пользователем",
        version = "1.0.0"))
public class AuthorizedUserController {

    private final UserInfoCommonDtoMapper userInfoCommonDtoMapper;

    private final TrackAuthorDtoMapper trackAuthorDtoMapper;

    private final TrackCommonDtoMapper trackCommonDtoMapper;

    private final TrackCreationDtoMapper trackCreationDtoMapper;

    private final AuthorizedUserService authorizedUserService;

    public AuthorizedUserController(@Autowired UserInfoCommonDtoMapper userInfoCommonDtoMapper,
                                    @Autowired TrackAuthorDtoMapper trackAuthorDtoMapper,
                                    @Autowired TrackCommonDtoMapper trackCommonDtoMapper,
                                    @Autowired TrackCreationDtoMapper trackCreationDtoMapper,
                                    @Autowired @Qualifier("authorized-user") AuthorizedUserService authorizedUserService) {
        this.userInfoCommonDtoMapper = userInfoCommonDtoMapper;
        this.trackAuthorDtoMapper = trackAuthorDtoMapper;
        this.trackCommonDtoMapper = trackCommonDtoMapper;
        this.trackCreationDtoMapper = trackCreationDtoMapper;
        this.authorizedUserService = authorizedUserService;
    }

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
    public ResponseEntity<List<UserInfoCommonDto>> getSubscriptions(@RequestParam("unitsOnPage") int unitsOnPage,
                                                                    @RequestParam("page") int page) {
        List<UserInfoCommonDto> dtoList = userInfoCommonDtoMapper.toDtoList(authorizedUserService.getSubscriptions(unitsOnPage, page).stream().toList());
        return ResponseEntity.ok(dtoList);
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
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<List<UserInfoCommonDto>> getSubscribers(@RequestParam("unitsOnPage") int unitsOnPage,
                                                                  @RequestParam("page") int page) {
        List<UserInfoCommonDto> dtoList = userInfoCommonDtoMapper.toDtoList(authorizedUserService.getSubscribers(unitsOnPage, page).stream().toList());
        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод подписывает авторизованного пользователя на другого пользователя",
            parameters = {
                    @Parameter(name = "id", description = "Id пользователя, на которого" +
                            "необходимо подписаться", required = true)
            }
    )
    @RequestMapping(
            path = "/subscribe",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> subscribe(@RequestParam("id") long id) {
        authorizedUserService.subscribe(id);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод отписывает авторизованного пользователя от другого пользователя",
            parameters = {
                    @Parameter(name = "id", description = "Id пользователя, от которого" +
                            "необходимо отписаться", required = true)
            }
    )
    @RequestMapping(
            path = "/unsubscribe",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> unsubscribe(@RequestParam("id") long id) {
        authorizedUserService.unsubscribe(id);
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
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<List<TrackAuthorDto>> getPublishedTracks(@RequestParam("unitsOnPage") int unitsOnPage,
                                                                   @RequestParam("page") int page) {
        List<TrackAuthorDto> dtoList = trackAuthorDtoMapper.toDtoList(authorizedUserService.getPublishedTracks(unitsOnPage, page).stream().toList());
        return ResponseEntity.ok(dtoList);
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
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<List<TrackCommonDto>> getPurchasedTracks(@RequestParam("unitsOnPage") int unitsOnPage,
                                                                   @RequestParam("page") int page) {
        List<TrackCommonDto> dtoList = trackCommonDtoMapper.toDtoList(authorizedUserService.getPurchasedTracks(unitsOnPage, page).stream().toList());
        return ResponseEntity.ok(dtoList);
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
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<List<TrackCommonDto>> getFavouriteTracks(@RequestParam("unitsOnPage") int unitsOnPage,
                                                                   @RequestParam("page") int page) {
        List<TrackCommonDto> dtoList = trackCommonDtoMapper.toDtoList(authorizedUserService.getFavouriteTracks(unitsOnPage, page).stream().toList());
        return ResponseEntity.ok(dtoList);
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
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<List<TrackCommonDto>> getBasketTracks(@RequestParam("unitsOnPage") int unitsOnPage,
                                                                @RequestParam("page") int page) {
        List<TrackCommonDto> dtoList = trackCommonDtoMapper.toDtoList(authorizedUserService.getBasketTracks(unitsOnPage, page).stream().toList());
        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод публикует трек",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Трек:
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
    @PreAuthorize("hasAuthority('beingAnAuthor')")
    public ResponseEntity<String> publishTrack(@RequestBody TrackCreationDto trackCreationDto) {
        Track track = trackCreationDtoMapper.toEntity(trackCreationDto);
        authorizedUserService.publishTrack(track);
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
    @PreAuthorize("hasAuthority('myProfileActions')")
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
    @PreAuthorize("hasAuthority('myProfileActions')")
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
    @PreAuthorize("hasAuthority('myProfileActions')")
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
    @PreAuthorize("hasAuthority('beingAnAuthor')")
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
    @PreAuthorize("hasAuthority('myProfileActions')")
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
    @PreAuthorize("hasAuthority('myProfileActions')")
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
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> clearBasket() {
        authorizedUserService.clearBasket();
        return ResponseEntity.ok("OK");
    }
}