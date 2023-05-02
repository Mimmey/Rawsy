package org.mimmey.controller.track;

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
@RequestMapping("published-tracks")
@OpenAPIDefinition(info = @Info(title = "RestController для работы со списком опубликованных треков",
        version = "1.0.0"))
public class PublishedTrackListController implements RelatedTrackListController {

    @Qualifier("published")
    private final TrackListService trackListService;

    @Operation(
            summary = "Метод возвращает список треков, опубликованных пользователем",
            parameters = {
                    @Parameter(name = "userId", description = "Id пользователя", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество треков на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/list",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    @Override
    public ResponseEntity<List<TrackDto>> getList(@RequestParam("userId") long userId,
                                                          @RequestParam("unitsOnPage") long unitsOnPage,
                                                          @RequestParam("page") long page) {
        return ResponseEntity.ok(trackListService.getList(userId, unitsOnPage, page));
    }
}
