package com.alperensertoglu.service;

import com.alperensertoglu.dto.request.DoLoginRequestDto;
import com.alperensertoglu.dto.request.RegisterRequestDto;
import com.alperensertoglu.exception.AuthServiceException;
import com.alperensertoglu.exception.EerrorType;
import com.alperensertoglu.mapper.IAuthMapper;
import com.alperensertoglu.repository.IAuthRepository;
import com.alperensertoglu.repository.entity.Auth;
import com.alperensertoglu.utility.JwtTokenManager;
import com.alperensertoglu.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository repository, JwtTokenManager jwtTokenManager){
        super(repository);
        this.repository=repository;
        this.jwtTokenManager = jwtTokenManager;
    }


   public Auth register(RegisterRequestDto dto){
        if(repository.existsByUsername(dto.getUsername()))
            throw new AuthServiceException(EerrorType.REGISTER_USERNAME_EXISTS);
       Auth auth=IAuthMapper.INSTANCE.toAuth(dto);
       return save(auth);
   }

    /**
     * Kullanıcıadı ve şifresi kullanılarak doğrulama yapar.
     * Doğrulama başarılı ise Özel bir kimlik ver.
     * @param dto
     * @return
     */
    public String doLogin(DoLoginRequestDto dto) {
        Optional<Auth> auth=repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if(auth.isEmpty()) throw new AuthServiceException(EerrorType.DOLOGIN_USERNAMEORPASSWORD_NOTEXISTS);

        return jwtTokenManager.createToken(auth.get().getId()).get();
    }

    public List<Auth> findAll(String token) {
        Optional<Long> id=null;
        try {
            id = jwtTokenManager.getIdFromToken(token);
        }catch (Exception exception){
            throw new AuthServiceException(EerrorType.INVALID_TOKEN);
        }
        if(findById(id.get()).isEmpty())
            throw new AuthServiceException(EerrorType.INVALID_TOKEN);
        return findAll();
    }
}
