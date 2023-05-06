package org.mimmey.entity.associative;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.embdded_keys.TrackInBasketPK;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "tracks_in_basket_list")
public class TrackInBasket {

    @EmbeddedId
    private TrackInBasketPK pk;
}
