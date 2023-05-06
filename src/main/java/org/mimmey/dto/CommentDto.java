package org.mimmey.dto;

import java.time.LocalDateTime;

public class CommentDto {

    private UserInfoDto commentAuthor;

    private TrackDto track;

    private String content;

    private byte rate;

    private LocalDateTime timestamp;
}
