package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.ExtendedTrackDto;
import org.mimmey.dto.TrackDto;
import org.mimmey.utils.Filter;
import org.mimmey.utils.SortingType;
import org.mimmey.service.TrackService;
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
@RequestMapping("tracks")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с треками",
        version = "1.0.0"))
public class TrackController {

    private final TrackService trackService;

    @Operation(
            summary = "Метод возвращает страницу глобального списка треков, названия которых содержат строку поиска. " +
                    "При этом применяются фильтры и сортировка",
            parameters = {
                    @Parameter(name = "filterList", description = "Список фильтров"),
                    @Parameter(name = "sortingType", description = "Тип сортировки"),
                    @Parameter(name = "searchString", description = "Строка поиска"),
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/global",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackDto>> getPublishedTrackList(@RequestParam("filterList") List<? extends Filter<?>> filterList,
                                                                @RequestParam("sortingType") SortingType sortingType,
                                                                @RequestParam("searchString") String searchString,
                                                                @RequestParam("unitsOnPage") long unitsOnPage,
                                                                @RequestParam("page") long page) {
        return ResponseEntity.ok(trackService.getGlobalTrackList(filterList, sortingType, searchString, unitsOnPage, page));
    }

    @Operation(
            summary = "Метод возвращает страницу списка треков от подписок, названия которых содержат строку поиска. " +
                    "При этом применяются фильтры и сортировка",
            parameters = {
                    @Parameter(name = "filterList", description = "Список фильтров"),
                    @Parameter(name = "sortingType", description = "Тип сортировки"),
                    @Parameter(name = "searchString", description = "Строка поиска"),
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/subscriptions",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackDto>> getSubscriptionsLastTrackList(@RequestParam("filterList") List<? extends Filter<?>> filterList,
                                                                        @RequestParam("sortingType") SortingType sortingType,
                                                                        @RequestParam("searchString") String searchString,
                                                                        @RequestParam("unitsOnPage") long unitsOnPage,
                                                                        @RequestParam("page") long page) {
        return ResponseEntity.ok(trackService.getSubscriptionsLastTrackList(filterList, sortingType, searchString, unitsOnPage, page));
    }

    @Operation(
            summary = "Метод возвращает страницу списка треков за неделю с наиболее высоким рейтингом, названия которых " +
                    "содержат строку поиска. При этом применяются фильтры",
            parameters = {
                    @Parameter(name = "filterList", description = "Список фильтров"),
                    @Parameter(name = "searchString", description = "Строка поиска"),
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/week-hottest",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackDto>> getHottestPerWeek(@RequestParam("filterList") List<? extends Filter<?>> filterList,
                                                            @RequestParam("searchString") String searchString,
                                                            @RequestParam("unitsOnPage") long unitsOnPage,
                                                            @RequestParam("page") long page) {
        return ResponseEntity.ok(trackService.getHottestPerWeek(filterList, searchString, unitsOnPage, page));
    }

    @Operation(
            summary = "Метод возвращает страницу списка новых за неделю треков, названия которых " +
                    "содержат строку поиска. При этом применяются фильтры",
            parameters = {
                    @Parameter(name = "filterList", description = "Список фильтров"),
                    @Parameter(name = "searchString", description = "Строка поиска"),
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/week-new",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackDto>> getNewPerWeek(@RequestParam("filterList") List<? extends Filter<?>> filterList,
                                                        @RequestParam("searchString") String searchString,
                                                        @RequestParam("unitsOnPage") long unitsOnPage,
                                                        @RequestParam("page") long page) {
        return ResponseEntity.ok(trackService.getNewPerWeek(filterList, searchString, unitsOnPage, page));
    }

    @Operation(
            summary = "Метод возвращает трек",
            parameters = {
                    @Parameter(name = "trackId", description = "Id трека", required = true)
            }
    )
    @RequestMapping(
            path = "/track",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<TrackDto> getTrack(@RequestParam("trackId") long trackId) {
        return ResponseEntity.ok(trackService.getTrack(trackId));
    }

    @Operation(
            summary = "Метод меняет цену трека",
            parameters = {
                    @Parameter(name = "trackId", description = "Id трека", required = true),
                    @Parameter(name = "newCost", description = "Новая цена трека", required = true)
            }
    )
    @RequestMapping(
            path = "/cost",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT
    )
    public ResponseEntity<String> changeCost(@RequestParam("trackId") long trackId,
                                             @RequestParam("newCost") long newCost) {
        trackService.changeCost(trackId, newCost);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод меняет информацию о треке",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Обновленная информмация о треке: " +
                    "\nREQUEST_ID - Id заявки, "
                    //TODO ДОПИСАТЬ
            )
    )
    @RequestMapping(
            path = "/track",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PATCH
    )
    public ResponseEntity<String> changeTrack(@RequestBody ExtendedTrackDto newTrackInfo) {
        trackService.changeTrack(newTrackInfo);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод скачивает архив с мультитреком",
            parameters = {
                    @Parameter(name = "trackId", description = "Id трека", required = true)
            }
    )
    @RequestMapping(
            path = "/download-multitrack",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<String> downloadMultitrack(@RequestParam("trackId") long trackId) {
        trackService.downloadMultitrack(trackId);
        return ResponseEntity.ok("OK");
    }
}