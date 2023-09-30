package com.alperensertoglu.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder// bir sınıftan nesne türetmeyi sağlar.
@Data //get set metodlarını otomatik tanımlar.
@NoArgsConstructor //boş constructor oluşturur.
@AllArgsConstructor //dolu constructor oluşturur.
@Entity
@Table(name = "tbluserprofile")
public class UserProfile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long authid;
    String username;
    String email;
    String password;
    String ad;
    String adres;
    String telefon;
    String avatar;
}
