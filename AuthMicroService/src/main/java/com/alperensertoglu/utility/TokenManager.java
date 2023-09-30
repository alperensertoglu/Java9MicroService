package com.alperensertoglu.utility;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {
    //1. token üretecek

    public String createToken(Long id){
        return "satistoken:"+id;
    }

    //2. üretilen tokendan bilgi çıkarımı yapacak.
    public Long getIdByToken(String token){
        //satistoken:5624
       return Long.parseLong(token.substring(token.indexOf(":")+1));
    }
}
