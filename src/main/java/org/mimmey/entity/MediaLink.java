package org.mimmey.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.embedded_keys.MediaLinkPK;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "media_link")
public class MediaLink implements Serializable {

    @EmbeddedId
    private MediaLinkPK pk;

    private String content;
}
