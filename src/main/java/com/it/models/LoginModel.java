package com.it.models;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginModel {

  @NotNull(message="Mobile Can't be Null")
  @NotEmpty(message="Mobile can't be Empty")
  @Length(min=10,max=10,message="Wrong Mobile Number")
  private String mobile;
  
  @NotNull(message="Password Can't be Null")
  @NotEmpty(message="Password Can't be Empty")
  private String pwd;
}
