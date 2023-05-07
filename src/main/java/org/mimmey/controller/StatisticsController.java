package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.entity.associative.FavouriteAddition;
import org.mimmey.entity.associative.Purchase;
import org.mimmey.service.common.StatisticsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("statistics")
@OpenAPIDefinition(info = @Info(title = "RestController для работы со статистикой",
        version = "1.0.0"))
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Operation(
            summary = "Метод возвращает страницу списка покупок треков авторизованного пользователя",
            parameters = {
                    @Parameter(name = "userId", description = "Id пользователя", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество покупок на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/purchases",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Purchase>> getSubscriberList(@RequestParam("userId") long userId,
                                                            @RequestParam("unitsOnPage") long unitsOnPage,
                                                            @RequestParam("page") long page) {
        return ResponseEntity.ok(statisticsService.getPurchasesOfMyTrackHistory(userId, unitsOnPage, page));
    }

    @Operation(
            summary = "Метод возвращает страницу списка добавления в избранное треков авторизованного пользователя",
            parameters = {
                    @Parameter(name = "userId", description = "Id пользователя", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество добавлени в избранное на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/favourite-additions",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<FavouriteAddition>> getAddingMyTracksToFavouritesHistory(@RequestParam("userId") long userId,
                                                                                        @RequestParam("unitsOnPage") long unitsOnPage,
                                                                                        @RequestParam("page") long page) {
        return ResponseEntity.ok(statisticsService.getAddingMyTracksToFavouritesHistory(userId, unitsOnPage, page));
    }
}

