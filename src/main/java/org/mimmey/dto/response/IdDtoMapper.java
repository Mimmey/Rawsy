package org.mimmey.dto.response;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IdDtoMapper<T extends Number> {

    public IdDto<T> toDto(T id) {
        return new IdDto<T>(id);
    }

    public List<IdDto<T>> toDtoList(List<T> ids) {
        return ids.stream().map(this::toDto).toList();
    }
}
