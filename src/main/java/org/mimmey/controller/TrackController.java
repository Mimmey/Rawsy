package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import org.mimmey.dto.request.search.TrackSearchDto;
import org.mimmey.dto.request.search.mapper.TrackFilterDtoMapper;
import org.mimmey.dto.request.search.mapper.TrackSortingTypeDtoMapper;
import org.mimmey.dto.response.common.TrackCommonDto;
import org.mimmey.dto.response.common.mapper.TrackCommonDtoMapper;
import org.mimmey.service.common.TrackService;
import org.mimmey.utils.TrackFilter;
import org.mimmey.utils.TrackSortingTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@OpenAPIDefinition(info = @Info(title = "RestController для работы с треками",
        version = "1.0.0"))
public class TrackController {

    private final TrackCommonDtoMapper trackCommonDtoMapper;

    private final TrackFilterDtoMapper trackFilterDtoMapper;

    private final TrackSortingTypeDtoMapper trackSortingTypeDtoMapper;

    private final TrackService trackService;

    public TrackController(@Autowired TrackCommonDtoMapper trackCommonDtoMapper,
                           @Autowired TrackFilterDtoMapper trackFilterDtoMapper,
                           @Autowired TrackSortingTypeDtoMapper trackSortingTypeDtoMapper,
                           @Autowired @Qualifier("common-track") TrackService trackService) {
        this.trackCommonDtoMapper = trackCommonDtoMapper;
        this.trackFilterDtoMapper = trackFilterDtoMapper;
        this.trackSortingTypeDtoMapper = trackSortingTypeDtoMapper;
        this.trackService = trackService;
    }

    @Operation(
            summary = "Метод возвращает страницу глобального списка треков, названия которых содержат строку поиска. " +
                    "При этом применяются фильтры и сортировка",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Запрос на поиск:
                                        
                        filters — список фильтров;
                        
                        sortingType — тип сортировки;
                        
                        searchString — строка поиска;
                        
                        page — номер страницы;
                        
                        unitsOnPage — количество треков на странице.
                        
                        
                    Каждый фильтр включает в себя следующие поля:
                        
                        property — название поля, для которого задается фильтр;
                        
                        value — необходимое значение поля.
                        
                        
                    Тип сортировки может иметь значения "new" (по убыванию даты и времени публикации) и "best" (по убыванию рейтинга)"""
            )
    )
    @RequestMapping(
            path = "/public/tracks",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackCommonDto>> getPublishedTrackList(@Valid @RequestBody TrackSearchDto trackSearchDto) {
        List<TrackFilter> filters = trackFilterDtoMapper.toEntityList(trackSearchDto.getFilters());
        TrackSortingTypes sortingType = trackSortingTypeDtoMapper.toEntity(trackSearchDto.getSortingType());

        List<TrackCommonDto> dtoList = trackCommonDtoMapper.toDtoList(trackService.getGlobalTracks(filters,
                        sortingType,
                        trackSearchDto.getSearchString(),
                        trackSearchDto.getPage() - 1,
                        trackSearchDto.getUnitsOnPage())
                .stream().toList());

        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод возвращает страницу списка треков за неделю с наиболее высоким рейтингом, названия которых " +
                    "содержат строку поиска. При этом применяются фильтры",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Запрос на поиск:
                        
                        filters — список фильтров;
                        
                        searchString — строка поиска
                        
                        page — номер страницы;
                        
                        unitsOnPage — количество треков на странице.
                        
                        
                    Каждый фильтр включает в себя следующие поля:
                        
                        property — название поля, для которого задается фильтр;
                        
                        value — необходимое значение поля"""
            )
    )
    @RequestMapping(
            path = "/public/tracks/week-hottest",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackCommonDto>> getHottestPerWeek(@Valid @RequestBody TrackSearchDto trackSearchDto) {
        List<TrackFilter> filters = trackFilterDtoMapper.toEntityList(trackSearchDto.getFilters());

        List<TrackCommonDto> dtoList = trackCommonDtoMapper.toDtoList(trackService.getHottestPerWeek(filters,
                        trackSearchDto.getSearchString(),
                        trackSearchDto.getPage() - 1,
                        trackSearchDto.getUnitsOnPage())
                .stream().toList());

        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод возвращает страницу списка новых за неделю треков, названия которых " +
                    "содержат строку поиска. При этом применяются фильтры",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Запрос на поиск:
                        
                        filters — список фильтров;
                        
                        searchString — строка поиска
                        
                        page — номер страницы;
                        
                        unitsOnPage — количество треков на странице.
                        
                        
                    Каждый фильтр включает в себя следующие поля:
                        
                        property — название поля, для которого задается фильтр;
                        
                        value — необходимое значение поля"""
            )
    )
    @RequestMapping(
            path = "/public/tracks/week-new",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<TrackCommonDto>> getNewPerWeek(@Valid @RequestBody TrackSearchDto trackSearchDto) {
        List<TrackFilter> filters = trackFilterDtoMapper.toEntityList(trackSearchDto.getFilters());

        List<TrackCommonDto> dtoList = trackCommonDtoMapper.toDtoList(trackService.getNewPerWeek(filters,
                        trackSearchDto.getSearchString(),
                        trackSearchDto.getPage() - 1,
                        trackSearchDto.getUnitsOnPage())
                .stream().toList());

        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод возвращает трек",
            parameters = {
                    @Parameter(name = "id", description = "Id трека", required = true)
            }
    )
    @RequestMapping(
            path = "/public/tracks/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<TrackCommonDto> getTrack(@PathVariable("id") long id) {
        TrackCommonDto dto = trackCommonDtoMapper.toDto(trackService.getTrack(id));
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Метод возвращает аудио превью трека",
            parameters = {
                    @Parameter(name = "id", description = "Id трека", required = true)
            }
    )
    @RequestMapping(
            path = "/public/tracks/{id}/preview",
            produces = "audio/wav",
            method = RequestMethod.GET
    )
    public ResponseEntity<byte[]> getTrackPreview(@PathVariable("id") long id) {
        byte[] preview = trackService.getTrackPreview(id);
        return ResponseEntity.ok(preview);
    }
}