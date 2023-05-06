package org.mimmey.entity.associative;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.embdded_keys.SubscriptionPK;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "subscription_list")
public class Subscription {

    @EmbeddedId
    private SubscriptionPK pk;
}
