package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.service.common.TrackService;
import org.mimmey.utils.Filter;
import org.mimmey.utils.SortingType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TrackCommonDto>> getPublishedTrackList(@RequestParam(value = "filterList", required = false) List<? extends Filter<?>> filterList,
                                                                      @RequestParam(value = "sortingType", required = false) SortingType sortingType,
                                                                      @RequestParam(value = "searchString", defaultValue = "") String searchString,
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
    public ResponseEntity<List<TrackCommonDto>> getSubscriptionsLastTrackList(@RequestParam(value = "filterList", required = false) List<? extends Filter<?>> filterList,
                                                                              @RequestParam(value = "sortingType", required = false) SortingType sortingType,
                                                                              @RequestParam(value = "searchString", defaultValue = "") String searchString,
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
    public ResponseEntity<List<TrackCommonDto>> getHottestPerWeek(@RequestParam(value = "filterList", required = false) List<? extends Filter<?>> filterList,
                                                                  @RequestParam(value = "searchString", defaultValue = "") String searchString,
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
    public ResponseEntity<List<TrackCommonDto>> getNewPerWeek(@RequestParam(value = "filterList", required = false) List<? extends Filter<?>> filterList,
                                                              @RequestParam(value = "searchString", defaultValue = "") String searchString,
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
    public ResponseEntity<TrackCommonDto> getTrack(@RequestParam("trackId") long trackId) {
        return ResponseEntity.ok(trackService.getTrack(trackId));
    }
}