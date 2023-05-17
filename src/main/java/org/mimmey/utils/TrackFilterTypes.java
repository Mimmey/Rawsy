package org.mimmey.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mimmey.entity.Track;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public enum TrackFilterTypes {

    IS_CYCLED("is_cycled", Boolean.class);

    private final String property;

    private final Class<? extends Serializable> valueClass;

    public static TrackFilterTypes getByProperty(String property) {
        for (TrackFilterTypes type : TrackFilterTypes.values()) {
            if (type.property.equals(property)) {
                return type;
            }
        }

        throw new RuntimeException("No such trackFilterType");
    }

    public Specification<Track> getSpecification(Object value) {
        try {
            return (track, cq, cb) -> cb.equal(track.get(this.property), valueClass.cast(value));
        } catch (ClassCastException e) {
            throw new RuntimeException(e);
        }
    }
}
