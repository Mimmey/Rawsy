package org.mimmey.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "media_link")
public class MediaLink implements Serializable {

    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    private String content;
}
