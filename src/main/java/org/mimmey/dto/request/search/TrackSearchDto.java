package org.mimmey.dto.request.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mimmey.utils.TrackSortingTypes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class TrackSearchDto {

    @JsonProperty(value = "filters", access = JsonProperty.Access.WRITE_ONLY)
    private List<TrackFilterDto> filters = new ArrayList<>();

    @JsonProperty(value = "sortingType", access = JsonProperty.Access.WRITE_ONLY)
    private String sortingType = TrackSortingTypes.NEW.getName();

    @JsonProperty(value = "searchString", access = JsonProperty.Access.WRITE_ONLY)
    private String searchString = "";

    @JsonProperty(value = "page", access = JsonProperty.Access.WRITE_ONLY)
    private Integer page = 1;

    @JsonProperty(value = "unitsOnPage", access = JsonProperty.Access.WRITE_ONLY)
    private Integer unitsOnPage = Integer.MAX_VALUE;
}
