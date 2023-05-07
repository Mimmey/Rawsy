package org.mimmey.dto.request.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotNull;
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
@JsonTypeName("user")
public class UserReportCreationDto extends ReportCreationDto {

    @NotNull
    @JsonProperty(value = "userSubjectId", access = JsonProperty.Access.WRITE_ONLY)
    private Long userSubjectId;
}
