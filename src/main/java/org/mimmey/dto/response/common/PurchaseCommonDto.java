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
public class PurchaseCommonDto {

    @JsonProperty(value = "purchaserId", access = JsonProperty.Access.READ_ONLY)
    private Long purchaserId;

    @JsonProperty(value = "trackId", access = JsonProperty.Access.READ_ONLY)
    private Long trackId;

    @JsonProperty(value = "cost", access = JsonProperty.Access.READ_ONLY)
    private Long cost;

    @JsonProperty(value = "timestamp", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime timestamp;
}
