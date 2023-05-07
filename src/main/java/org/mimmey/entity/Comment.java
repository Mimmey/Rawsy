package org.mimmey.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.embedded_keys.CommentPK;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @EmbeddedId
    private CommentPK pk;

    private String content;

    private Byte rate;

    private LocalDateTime timestamp;
}
