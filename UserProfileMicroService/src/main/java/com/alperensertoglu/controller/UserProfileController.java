package com.alperensertoglu.controller;

import static com.alperensertoglu.constants.EndPoints.*;

import com.alperensertoglu.dto.request.UserProfileSaveRequestDto;
import com.alperensertoglu.repository.entity.UserProfile;
import com.alperensertoglu.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto){
       return ResponseEntity.ok(userProfileService.saveDto(dto));
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<UserProfile>> findAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }
}
