package org.kevin;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Convert<T, U> {

    private final Function<T, U> fromDto;

    private final Function<U, T> fromEntity;

    /**
     * dto -> entity
     *
     * @param dto dto
     * @return entity
     */
    public final U convertFromDto(final T dto) {
        return fromDto.apply(dto);
    }

    /**
     * entity -> dto
     *
     * @param entity entity
     * @return dto
     */
    public final T convertFromEntity(final U entity) {
        return fromEntity.apply(entity);
    }

    /**
     * dtoList -> entityList
     * @param dtoList dtoList
     * @return List<U>
     */
    public final List<U> createFromDtoList(final Collection<T> dtoList) {
        return dtoList.stream().map(this::convertFromDto).collect(Collectors.toList());
    }

    /**
     * entityList -> dtoList
     * @param entityList entityList
     * @return List<T>
     */
    public final List<T> createFromEntityList(final Collection<U> entityList) {
        return entityList.stream().map(this::convertFromEntity).collect(Collectors.toList());
    }
}
