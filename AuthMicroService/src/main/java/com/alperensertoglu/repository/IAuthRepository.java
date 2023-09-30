package com.alperensertoglu.repository;

import com.alperensertoglu.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {
    /**
     * Username daha önce kullanılmış mı kontrol etmek için.
     * @param username
     * @return
     */
    Boolean existsByUsername(String username);
    /**
     * Username ve password kayıtlı mı değil mi?
     */
    Optional<Auth> findOptionalByUsernameAndPassword(String username, String password);


}
