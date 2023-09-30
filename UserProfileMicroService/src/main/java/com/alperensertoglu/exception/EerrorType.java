package com.alperensertoglu.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EerrorType {
    MUSTERI_BULUNAMADI(1003,"Aradığınız müşteri sistemde kayıtlı değil",NOT_FOUND),
    REGISTER_PASSWORD_MISMATCH(1004,"Girilen parolalar uyuşmadı.",BAD_REQUEST),
    REGISTER_USERNAME_EXISTS(1005,"Kullanıcı adı daha önce alınmış.",BAD_REQUEST),
    DOLOGIN_USERNAMEORPASSWORD_NOTEXISTS(1006,"Kullanıcı adı veya şifre hatalı.",BAD_REQUEST),
    INVALID_TOKEN(1007,"Geçersiz token.",BAD_REQUEST),
    URUN_EKLEME_HATASI(2001,"Ürün ekleme başarısız oldu",INTERNAL_SERVER_ERROR),
    INVALID_PARAMETER(3001,"Geçersiz parametre girişi yaptınız",BAD_REQUEST);

    private int code;
    private String mesaj;
    private HttpStatus status;
}
