package org.mimmey.dto.response.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserReportAdminDto.class),
        @JsonSubTypes.Type(value = TrackReportAdminDto.class)
})
public abstract class ReportAdminDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "authorId", access = JsonProperty.Access.READ_ONLY)
    private Long authorId;

    @JsonProperty(value = "content", access = JsonProperty.Access.READ_ONLY)
    private String content;

    @JsonProperty(value = "timestamp", access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDateTime timestamp;
}
