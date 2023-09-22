package org.mimmey.dto.response.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class FavouriteAdditionCommonDto {

    @JsonProperty(value = "ownerId", access = JsonProperty.Access.READ_ONLY)
    private Long ownerId;

    @JsonProperty(value = "trackId", access = JsonProperty.Access.READ_ONLY)
    private Long trackId;

    @JsonProperty(value = "timestamp", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime timestamp;
}
