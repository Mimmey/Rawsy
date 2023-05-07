package org.mimmey.entity.embedded_keys;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.mimmey.entity.User;

import java.io.Serializable;

@Data
public class MediaLinkPK implements Serializable {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
