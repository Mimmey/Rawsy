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
@Table(name = "report")
public class Report implements Serializable {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private String content;

    private LocalDateTime timestamp;
}
