package com.alperensertoglu.mapper;

import com.alperensertoglu.dto.request.RegisterRequestDto;
import com.alperensertoglu.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);
    Auth toAuth(final RegisterRequestDto dto);
}
