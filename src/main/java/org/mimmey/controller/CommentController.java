package org.mimmey.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import org.mimmey.dto.request.creation.CommentCreationDto;
import org.mimmey.dto.request.creation.mappers.CommentCreationDtoMapper;
import org.mimmey.dto.response.common.CommentCommonDto;
import org.mimmey.dto.response.common.mapper.CommentCommonDtoMapper;
import org.mimmey.entity.Comment;
import org.mimmey.service.common.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@OpenAPIDefinition(info = @Info(title = "RestController для работы с комментариями",
        version = "1.0.0"))
public final class CommentController {

    private final CommentCommonDtoMapper commentCommonDtoMapper;

    private final CommentCreationDtoMapper commentCreationDtoMapper;

    private final CommentService commentService;

    public CommentController(@Autowired CommentCommonDtoMapper commentCommonDtoMapper,
                             @Autowired CommentCreationDtoMapper commentCreationDtoMapper,
                             @Autowired CommentService commentService) {
        this.commentCommonDtoMapper = commentCommonDtoMapper;
        this.commentCreationDtoMapper = commentCreationDtoMapper;
        this.commentService = commentService;
    }

    @Operation(
            summary = "Метод публикует комментарий",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = """
                    Комментарий:
                                        
                        
                        trackId — ID трека;
                        
                        content — содержимое комментария;
                        
                        rate — оценка трека (целое число от 1 до 5)"""
            )
    )
    @RequestMapping(
            path = "comment/publish",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    @PreAuthorize("hasAuthority('beingAnAuthor')")
    public ResponseEntity<String> createComment(@RequestBody CommentCreationDto commentCreationDto) {
        Comment comment = commentCreationDtoMapper.toEntity(commentCreationDto);
        commentService.createComment(comment);
        return ResponseEntity.ok("OK");
    }

    @Operation(
            summary = "Метод возвращает страницу списка комментариев заданного трека",
            parameters = {
                    @Parameter(name = "id", description = "Id трека", required = true),
                    @Parameter(name = "page", description = "Номер страницы", required = true),
                    @Parameter(name = "unitsOnPage", description = "Количество комментариев на странице", required = true)
            }
    )
    @RequestMapping(
            path = "/track/{id}/comments",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity<List<CommentCommonDto>> getTrackCommentList(@PathVariable("id") long id,
                                                                      @RequestParam("page") int page,
                                                                      @RequestParam("unitsOnPage") int unitsOnPage) {
        List<CommentCommonDto> dtoList = commentCommonDtoMapper.toDtoList(
                commentService.getTrackComments(id, page - 1, unitsOnPage).stream().toList()
        );
        return ResponseEntity.ok(dtoList);
    }

    @Operation(
            summary = "Метод удаляет комментарий",
            parameters = {
                    @Parameter(name = "trackId", description = "ID трека", required = true),
                    @Parameter(name = "authorId", description = "ID автора комментария", required = true)
            }
    )
    @RequestMapping(
            path = "/track/{trackId}/comment",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('beingAnAuthor')")
    public ResponseEntity<String> removeComment(@PathVariable("trackId") long trackId,
                                                @RequestParam("authorId") long authorId) {
        commentService.removeComment(authorId, trackId);
        return ResponseEntity.ok("OK");
    }
}

