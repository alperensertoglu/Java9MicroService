package com.alperensertoglu.service;

import com.alperensertoglu.dto.request.UserProfileSaveRequestDto;
import com.alperensertoglu.mapper.IUserProfileMapper;
import com.alperensertoglu.repository.IUserProfileRepository;
import com.alperensertoglu.repository.entity.UserProfile;
import com.alperensertoglu.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository userProfileRepository;

    public UserProfileService(IUserProfileRepository userProfileRepository){
        super(userProfileRepository);
        this.userProfileRepository=userProfileRepository;
    }

    public Boolean saveDto(UserProfileSaveRequestDto dto) {
        save(IUserProfileMapper.INSTANCE.toUserProfile(dto));
        return true;
    }
}
