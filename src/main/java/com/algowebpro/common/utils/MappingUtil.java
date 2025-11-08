package com.algowebpro.common.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MappingUtil {

	private final ModelMapper modelMapper;

	public <D, T> D convertToDto(final T entity, Class<D> dtoClass) {
		return modelMapper.map(entity, dtoClass);
	}

	public <D, T> List<D> convertToDtoList(final List<T> entityList, Class<D> dtoClass) {
		return entityList.stream().map(entity -> modelMapper.map(entity, dtoClass)).collect(Collectors.toList());
	}
	
	public <T, D> T convertToEntity(final D dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

}
