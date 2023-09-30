package com.alperensertoglu.mapper;

import com.alperensertoglu.dto.request.UserProfileSaveRequestDto;
import com.alperensertoglu.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {
    IUserProfileMapper INSTANCE= Mappers.getMapper(IUserProfileMapper.class);
    UserProfile toUserProfile(final UserProfileSaveRequestDto dto);
}
