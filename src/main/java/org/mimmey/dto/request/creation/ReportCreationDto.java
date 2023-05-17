package org.mimmey.dto.request.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
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
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserReportCreationDto.class),
        @JsonSubTypes.Type(value = TrackReportCreationDto.class)
})
public abstract class ReportCreationDto {

    @NotBlank
    @JsonProperty(value = "content", access = JsonProperty.Access.WRITE_ONLY)
    protected String content;
}
