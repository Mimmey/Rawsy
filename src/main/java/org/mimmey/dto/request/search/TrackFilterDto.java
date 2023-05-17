package org.mimmey.dto.request.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class TrackFilterDto {

    @JsonProperty(value = "property", access = JsonProperty.Access.WRITE_ONLY)
    private String property;

    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private Object value;
}