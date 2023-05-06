package org.mimmey.dto;

import org.mimmey.entity.Track;

import java.time.LocalDateTime;

public class CommentDto {

    private UserInfoDto commentAuthor;

    private Track trackId;

    private String content;

    private byte rate;

    private LocalDateTime timestamp;
}
