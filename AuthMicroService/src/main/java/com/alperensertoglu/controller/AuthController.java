package com.alperensertoglu.controller;

import static com.alperensertoglu.constants.EndPoints.*;

import com.alperensertoglu.dto.request.DoLoginRequestDto;
import com.alperensertoglu.dto.request.RegisterRequestDto;
import com.alperensertoglu.exception.AuthServiceException;
import com.alperensertoglu.exception.EerrorType;
import com.alperensertoglu.repository.entity.Auth;
import com.alperensertoglu.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * register işlemleri
     */
    @PostMapping(REGISTER)
    public ResponseEntity<Auth> register(@RequestBody RegisterRequestDto dto){
       if(!dto.getPassword().equals(dto.getRepassword()))
           throw new AuthServiceException(EerrorType.REGISTER_PASSWORD_MISMATCH);
        return ResponseEntity.ok(authService.register(dto));
    }

    /**
     * login işlemleri
     */
    @PostMapping(LOGIN)
    public ResponseEntity<String> dologin(@RequestBody DoLoginRequestDto dto){
      return ResponseEntity.ok(authService.doLogin(dto));
    }

@GetMapping(GETALL)
    public ResponseEntity<List<Auth>> findAll(String token){
        return ResponseEntity.ok(authService.findAll(token));
    }
}
