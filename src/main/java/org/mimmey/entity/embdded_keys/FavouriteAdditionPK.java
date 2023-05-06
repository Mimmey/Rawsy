package org.mimmey.entity.embdded_keys;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.mimmey.entity.Track;
import org.mimmey.entity.User;

import java.io.Serializable;

@Data
public class FavouriteAdditionPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track track;
}
