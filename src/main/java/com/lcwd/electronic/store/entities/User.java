package com.lcwd.electronic.store.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class User {

    @Id
    private String userId;

    @Column(name ="User_name")
    private String name;

    @Column(name ="User_email", unique = true)
    private String email;

    @Column(name = "user_password", length = 10)
    private String password;

    private String gender;

    @Column(length = 1000)
    private String about;

    @Column(name = "User_image_name")
    private String imageName;


}
