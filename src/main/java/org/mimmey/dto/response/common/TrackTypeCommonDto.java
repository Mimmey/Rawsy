package org.mimmey.dto.response.common;

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
public class TrackTypeCommonDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @JsonProperty(value = "name", access = JsonProperty.Access.READ_ONLY)
    private String name;
}
