package org.mimmey.entity.embedded_keys;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.mimmey.entity.Track;
import org.mimmey.entity.User;

import java.io.Serializable;

@Data
public class PurchasePK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "purchaser_id")
    private User purchaser;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track track;
}
