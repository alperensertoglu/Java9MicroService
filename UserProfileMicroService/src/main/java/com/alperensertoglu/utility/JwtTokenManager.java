package com.alperensertoglu.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager {
    @Value("${authservices.secrets.secret-key}")
    String secretKey;
    @Value("${authservices.secrets.issuer}")
    String issuer;
    Long exDate = 1000L * 60; //1dk

    //1. token üret.

    /**
     * Dikkat dikkat:
     * Claim objelerinin içine herkes tarafından görülmesini istemediğiniz bilgileri koymamalısınız.
     * E-mail, password gibi bilgiler payload içinde olmamalıdır.
     * @param id
     * @return
     */
    public Optional<String> createToken(Long id) {
        String token = "";
        try {
            token = JWT.create().withAudience()
                    .withClaim("id", id)
                    .withClaim("whichpage", "AuthMicroService")
                    .withClaim("lastjoin", System.currentTimeMillis())
                    .withClaim("ders", "Java JWT")
                    .withClaim("grup", "Java 9")
                    .withIssuer(issuer) //jwt token oluşturan yapı
                    .withIssuedAt(new Date()) //jwt token oluşturulma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis() + exDate))
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        } catch (Exception ex) {
            return Optional.empty();
        }

    }

    //2. tokeni doğrula
    public Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) return false;
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    //3. tokendan bilgi çıkarımı yap.
    public Optional<Long> getIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) return Optional.empty() ;
            Long id=decodedJWT.getClaim("id").asLong();
            String whichpage=decodedJWT.getClaim("whichpage").asString();
            //System.out.println("Tokendaki which page:"+whichpage);
            return Optional.of(id);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }
}
