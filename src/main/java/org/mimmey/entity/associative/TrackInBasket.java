package org.mimmey.entity.associative;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.embedded_keys.TrackInBasketPK;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tracks_in_basket_list")
public class TrackInBasket {

    @EmbeddedId
    private TrackInBasketPK pk;
}
