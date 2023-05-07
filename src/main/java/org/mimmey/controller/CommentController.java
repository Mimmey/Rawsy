package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.mimmey.dto.request.creation.CommentCreationDto;
import org.mimmey.dto.response.common.CommentCommonDto;
import org.mimmey.service.common.CommentService;
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
@RequestMapping("comments")
@OpenAPIDefinition(info = @Info(title = "RestController для работы с комментариями",
        version = "1.0.0"))
public class CommentController {

    private final CommentService commentService;

    @Operation(
            summary = "Метод публикует комментарий",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Комментарий:\s
                        trackId — ID трека;
                        content — содержимое комментария;
                        rate — оценка трека (целое число от 1 до 5)"""
            )
    )
    @RequestMapping(
            path = "/publish",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<String> createComment(@RequestBody CommentCreationDto commentCreationDto) {
        commentService.createComment(commentCreationDto);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод возвращает страницу списка комментариев заданного трека",
            parameters = {
                    @Parameter(name = "trackId", description = "Id трека", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество комментариев на странице", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true)
            }
    )
    @RequestMapping(
            path = "/track-comments",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<CommentCommonDto>> getTrackCommentList(@RequestParam("trackId") long trackId,
                                                                      @RequestParam("unitsOnPage") long unitsOnPage,
                                                                      @RequestParam("page") long page) {
        return ResponseEntity.ok(commentService.getTrackCommentList(trackId, unitsOnPage, page));
    }

    @Operation(
            summary = "Метод удаляет комментарий",
            parameters = {
                    @Parameter(name = "trackId", description = "ID трека", required = true),
                    @Parameter(name = "commentId", description = "ID комментария", required = true)
            }
    )
    @RequestMapping(
            path = "/comment",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    public ResponseEntity<String> removeComment(@RequestParam("trackId") long trackId,
                                                @RequestParam("commentId") long commentId) {
        commentService.removeComment(trackId, commentId);
        return ResponseEntity.ok("OK");
    }
}

