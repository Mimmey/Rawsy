package org.mimmey.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.mimmey.entity.Track;
import org.springframework.data.jpa.domain.Specification;

@Data
@AllArgsConstructor
public class TrackFilter {

    private TrackFilterTypes filterType;
    private Object value;

    public Specification<Track> getSpecification() {
        return TrackFilterTypes.getSpecification(filterType, value);
    }
}
