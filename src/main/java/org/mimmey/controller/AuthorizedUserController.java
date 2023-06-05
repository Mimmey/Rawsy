package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
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
                    @Parameter(name = "page", description = "Номер страницы", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество подписок на странице", required = true)
            }
    )
    @RequestMapping(
            path = "/my/subscriptions",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<UserInfoCommonDto>> getSubscriptions(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                    @RequestParam(value = "unitsOnPage", defaultValue = "2147483647") int unitsOnPage) {
        List<UserInfoCommonDto> dtoList = userInfoCommonDtoMapper.toDtoList(
                authorizedUserService.getSubscriptions(page - 1, unitsOnPage).stream().toList()
        );
        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод возвращает страницу списка подписчиков авторизованного пользователя",
            parameters = {
                    @Parameter(name = "page", description = "Номер страницы", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество подписчиков на странице", required = true)
            }
    )
    @RequestMapping(
            path = "/my/subscribers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<List<UserInfoCommonDto>> getSubscribers(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                  @RequestParam(value = "unitsOnPage", defaultValue = "2147483647") int unitsOnPage) {
        List<UserInfoCommonDto> dtoList = userInfoCommonDtoMapper.toDtoList(
                authorizedUserService.getSubscribers(page - 1, unitsOnPage).stream().toList()
        );
        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод подписывает авторизованного пользователя на другого пользователя",
            parameters = {
                    @Parameter(name = "id", description = "Id пользователя, на которого " +
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
                    @Parameter(name = "id", description = "Id пользователя, от которого " +
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
                    @Parameter(name = "page", description = "Номер страницы", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true)
            }
    )
    @RequestMapping(
            path = "/my/published/tracks",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<List<TrackAuthorDto>> getPublishedTracks(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                   @RequestParam(value = "unitsOnPage", defaultValue = "2147483647") int unitsOnPage) {
        List<TrackAuthorDto> dtoList = trackAuthorDtoMapper.toDtoList(
                authorizedUserService.getPublishedTracks(page - 1, unitsOnPage).stream().toList()
        );
        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод возвращает страницу списка треков, приобретенных авторизованным пользователем",
            parameters = {
                    @Parameter(name = "page", description = "Номер страницы", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true)
            }
    )
    @RequestMapping(
            path = "/my/purchased/tracks",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<List<TrackCommonDto>> getPurchasedTracks(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                   @RequestParam(value = "unitsOnPage", defaultValue = "2147483647") int unitsOnPage) {
        List<TrackCommonDto> dtoList = trackCommonDtoMapper.toDtoList(
                authorizedUserService.getPurchasedTracks(page - 1, unitsOnPage).stream().toList()
        );
        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод возвращает страницу списка треков, находящихся в избранном у авторизованного пользователя",
            parameters = {
                    @Parameter(name = "page", description = "Номер страницы", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true)
            }
    )
    @RequestMapping(
            path = "/my/favourites/tracks",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<List<TrackCommonDto>> getFavouriteTracks(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                   @RequestParam(value = "unitsOnPage", defaultValue = "2147483647") int unitsOnPage) {
        List<TrackCommonDto> dtoList = trackCommonDtoMapper.toDtoList(
                authorizedUserService.getFavouriteTracks(page - 1, unitsOnPage).stream().toList()
        );
        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод возвращает страницу списка треков, находящихся в корзине у авторизованного пользователя",
            parameters = {
                    @Parameter(name = "page", description = "Номер страницы", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true)
            }
    )
    @RequestMapping(
            path = "/my/basket/tracks",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<List<TrackCommonDto>> getBasketTracks(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                @RequestParam(value = "unitsOnPage", defaultValue = "2147483647") int unitsOnPage) {
        List<TrackCommonDto> dtoList = trackCommonDtoMapper.toDtoList(
                authorizedUserService.getBasketTracks(page - 1, unitsOnPage).stream().toList()
        );
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
            path = "/public/tracks/publish",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @PreAuthorize("hasAuthority('beingAnAuthor')")
    public ResponseEntity<String> publishTrack(@Valid @RequestBody TrackCreationDto trackCreationDto) {
        Track track = trackCreationDtoMapper.toEntity(trackCreationDto);
        authorizedUserService.publishTrack(track);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод производит покупку трека",
            parameters = {
                    @Parameter(name = "id", description = "ID трека", required = true)
            }
    )
    @RequestMapping(
            path = "/public/tracks/{id}/purchase",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> purchaseTrack(@PathVariable("id") long id) {
        authorizedUserService.purchaseTrack(id);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод добавляет трек в избранное",
            parameters = {
                    @Parameter(name = "id", description = "ID трека", required = true)
            }
    )
    @RequestMapping(
            path = "/public/tracks/{id}/add-to-favourites",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> addTrackToFavorites(@PathVariable("id") long id) {
        authorizedUserService.addTrackToFavorites(id);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод добавляет трек в корзину",
            parameters = {
                    @Parameter(name = "id", description = "ID трека", required = true)
            }
    )
    @RequestMapping(
            path = "/public/tracks/{id}/add-to-basket",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> addTrackToBasket(@PathVariable("id") long id) {
        authorizedUserService.addTrackToBasket(id);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод удаляет опубликованный трек",
            parameters = {
                    @Parameter(name = "id", description = "ID трека", required = true)
            }
    )
    @RequestMapping(
            path = "/my/published/tracks/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('beingAnAuthor')")
    public ResponseEntity<String> deletePublishedTrack(@PathVariable("id") long id) {
        authorizedUserService.deletePublishedTrack(id);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод удаляет трек из избранного",
            parameters = {
                    @Parameter(name = "id", description = "ID трека", required = true)
            }
    )
    @RequestMapping(
            path = "/my/favourites/tracks/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> removeTrackFromFavourites(@PathVariable("id") long id) {
        authorizedUserService.removeTrackFromFavourites(id);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод удаляет трек из корзины",
            parameters = {
                    @Parameter(name = "id", description = "ID трека", required = true)
            }
    )
    @RequestMapping(
            path = "/my/basket/tracks/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> removeTrackFromBasket(@PathVariable("id") long id) {
        authorizedUserService.removeTrackFromBasket(id);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод очищает корзину"
    )
    @RequestMapping(
            path = "/my/basket/clear",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> clearBasket() {
        authorizedUserService.clearBasket();
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод получает общую стоимость треков в корзине"
    )
    @RequestMapping(
            path = "/my/basket/cost",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<Long> getBasketCost() {
        return ResponseEntity.ok(authorizedUserService.getBasketCost());
    }

    @Operation(
            summary = "Метод производит покупку всех треков в корзине"
    )
    @RequestMapping(
            path = "/my/basket/pay",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @PreAuthorize("hasAuthority('myProfileActions')")
    public ResponseEntity<String> payForBasket() {
        authorizedUserService.payForBasket();
        return ResponseEntity.ok("OK");
    }
}