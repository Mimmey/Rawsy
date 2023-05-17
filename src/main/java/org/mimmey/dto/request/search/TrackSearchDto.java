package org.mimmey.dto.request.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class TrackSearchDto {

    @JsonProperty(value = "filters", access = JsonProperty.Access.WRITE_ONLY)
    private List<TrackFilterDto> filters;

    @JsonProperty(value = "sortingType", access = JsonProperty.Access.WRITE_ONLY)
    private String sortingType;

    @JsonProperty(value = "searchString", access = JsonProperty.Access.WRITE_ONLY)
    private String searchString;

    @JsonProperty(value = "page", access = JsonProperty.Access.WRITE_ONLY)
    private Integer page;

    @JsonProperty(value = "unitsOnPage", access = JsonProperty.Access.WRITE_ONLY)
    private Integer unitsOnPage;
}
