package org.mimmey.utils;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mimmey.entity.Track;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum TrackFilterTypes {

    HAS_VOCAL("hasVocal", Boolean.class, SpecificationTypes.UNARY),

    IS_CYCLED("isCycled", Boolean.class, SpecificationTypes.UNARY),

    BPM("bpm", List.class, SpecificationTypes.INTEGER_INTERVAL),

    DURATION("duration", List.class, SpecificationTypes.INTEGER_INTERVAL),

    COST("cost", List.class, SpecificationTypes.LONG_INTERVAL);

    private final String property;

    private final Class<?> valueClass;

    private final SpecificationTypes specificationType;

    public static TrackFilterTypes getByProperty(String property) {
        for (TrackFilterTypes type : TrackFilterTypes.values()) {
            if (type.property.equals(property)) {
                return type;
            }
        }

        throw new EntityNotFoundException("No such trackFilterType");
    }

    public static Specification<Track> getSpecification(TrackFilterTypes trackFilterType, Object obj) {
        return trackFilterType.specificationType.getSpecification(trackFilterType, obj);
    }

    private enum SpecificationTypes {

        UNARY {
            @Override
            public Specification<Track> getSpecification(TrackFilterTypes type, Object obj) {
                try {
                    return (track, cq, cb) -> cb.equal(track.get(type.property), type.valueClass.cast(obj));
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        },

        CONTAINS {
            @Override
            public Specification<Track> getSpecification(TrackFilterTypes type, Object obj) {
                try {
                    List<?> list = (List<?>) obj;
                    List<Specification<Track>> specifications = new ArrayList<>();

                    for (Object value : list) {
                        specifications.add((track, cq, cb) -> cb.equal(track.get(type.property), type.valueClass.cast(value)));
                    }

                    return Specification.anyOf(specifications);
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        },

        INTEGER_INTERVAL {
            @Override
            public Specification<Track> getSpecification(TrackFilterTypes type, Object obj) {
                try {
                    List<?> list = (List<?>) obj;
                    return (track, cq, cb) -> cb.between(track.get(type.property),
                            (Integer) list.get(0),
                            (Integer) list.get(1));
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            }
        },

        LONG_INTERVAL {
            @Override
            public Specification<Track> getSpecification(TrackFilterTypes type, Object obj) {
                try {
                    List<?> list = (List<?>) obj;
                    return (track, cq, cb) -> cb.between(track.get(type.property),
                            (Long) list.get(0),
                            (Long) list.get(1));
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            }
        },

        DOUBLE_INTERVAL {
            @Override
            public Specification<Track> getSpecification(TrackFilterTypes type, Object obj) {
                try {
                    List<?> list = (List<?>) obj;
                    return (track, cq, cb) -> cb.between(track.get(type.property),
                            (Double) list.get(0),
                            (Double) list.get(0));
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        };

        public abstract Specification<Track> getSpecification(TrackFilterTypes type, Object obj);
    }
}
