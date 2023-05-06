package org.mimmey.entity.embdded_keys;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.mimmey.entity.User;

import java.io.Serializable;

@Data
public class SubscriptionPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private User subscriber;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private User subject;
}
