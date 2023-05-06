package org.mimmey.entity.associative;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.embdded_keys.FavouriteAdditionPK;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "favourites_list")
public class FavouriteAddition implements Serializable {

    @EmbeddedId
    private FavouriteAdditionPK pk;

    private LocalDateTime timestamp;
}
