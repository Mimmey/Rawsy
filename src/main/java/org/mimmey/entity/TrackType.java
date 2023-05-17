package org.mimmey.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "track_type")
public class TrackType implements Serializable {

    @Id
    private Long id;

    @Column(name = "_name")
    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Track> tracks;
}
