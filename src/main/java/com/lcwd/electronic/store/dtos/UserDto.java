package com.lcwd.electronic.store.dtos;

import com.lcwd.electronic.store.validate.imageNameValid;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {


    private String userId;

    @Size(min = 3, max = 15, message = "Invalid Name!!")
    private String name;

  //  @Email(message = "Invalid User Email!!")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid User Email")
    @NotBlank(message = "Email is not blank!!")
    private String email;

    @NotBlank(message = "Password is required!!")
    private String password;

    @Size(min = 4, max = 6, message = "Invalid gender!!")
    private String gender;

    @NotBlank(message = "Write something about yourself!!")
    private String about;

     //@Pattern


     //Custom Validator

    @imageNameValid
    private String imageName;

}
