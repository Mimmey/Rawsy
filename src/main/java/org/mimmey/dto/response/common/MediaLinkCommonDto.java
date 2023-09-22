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
public class MediaLinkCommonDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "ownerId", access = JsonProperty.Access.READ_ONLY)
    private Long ownerId;

    @JsonProperty(value = "content", access = JsonProperty.Access.READ_ONLY)
    private String content;
}
