package org.mimmey.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
@AllArgsConstructor
public enum TrackSortingTypes {

    NEW("new", Sort.by(Sort.Direction.DESC, "timestamp")),
    BEST("best", Sort.by(Sort.Direction.DESC, "rating"));

    private final String name;
    private final Sort sort;

    public static TrackSortingTypes getByName(String name) {
        for (TrackSortingTypes type : TrackSortingTypes.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }

        throw new RuntimeException("No such trackSortingType");
    }
}
