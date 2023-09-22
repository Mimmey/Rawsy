package org.mimmey.entity.associative;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.entity.embedded_keys.PurchasePK;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchase_list")
public class Purchase implements Serializable {

    @EmbeddedId
    private PurchasePK pk;

    private Long cost;

    @Column(name = "_timestamp")
    private LocalDateTime timestamp = LocalDateTime.now();
}
