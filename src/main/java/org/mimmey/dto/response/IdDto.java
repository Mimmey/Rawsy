package org.mimmey.dto.response;

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
public class IdDto<T extends Number> {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    protected T id;
}