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
        return (track, cq, cb) -> cb.equal(track.get(this.filterType.getProperty()),
                this.filterType.getValueClass().cast(value));
    }
}
